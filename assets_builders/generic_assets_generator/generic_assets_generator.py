
from __future__ import annotations
from typing import List

from os import path, makedirs
import json
from PIL import Image, ImageOps

from termcolor import cprint, COLORS


class TerminalColors:
    HEADER    = "\033[95m"
    OKBLUE    = "\033[94m"
    OKGREEN   = "\033[92m"
    WARNING   = "\033[93m"
    FAIL      = "\033[91m"
    ENDC      = "\033[0m"
    BOLD      = "\033[1m"
    UNDERLINE = "\033[4m"


class Settings:
    def __init__(self, outputPath: str, texturesPath: str, colorMode: str, fileFormat: str):
        self.outputPath = outputPath
        self.texturesPath = texturesPath
        self.colorMode = colorMode
        self.fileFormat = fileFormat
    
    @staticmethod
    def parse(config: dict) -> Settings:
        return Settings(**config)


def generateTexture(obj: Texturizeable, settings: Settings) -> Image:
    texturePath = path.join(settings.texturesPath, obj.texture)
    basePath = texturePath + "_base" + path.extsep + "png"
    grayScalePath = texturePath + "_grayscale" + path.extsep + "png"

    grayScale = Image.open(grayScalePath, "r")
    source = None
    # Make sure that path exists
    if (path.exists(basePath)):
        source = Image.open(basePath, "r")
    else:
        # If the path doesn't exist then create a new empty image
        source = Image.new("RGBA", grayScale.size, "#00000000")

    _, _, _, grayScaleAlpha = grayScale.split()
    colorizedGrayScale = ImageOps.colorize(grayScale.convert("L"), "#000000", obj.color)
    colorizedGrayScale.putalpha(grayScaleAlpha)

    finalTexture = Image.alpha_composite(source, colorizedGrayScale)
    return finalTexture.convert(settings.colorMode)


class Texturizeable:
    def __init__(self, id: str, texture: str, color: str):
        self.id = id
        self.texture = texture
        self.color = color
    
    def getFileName(self) -> str:
        return self.id.split(":").pop()
    
    def getNamespace(self) -> str:
        return self.id.split(":")[0]

    def getTexture(self, type: str) -> str:
        return self.getNamespace() + ":" + type + "s/" + self.getFileName()
    
    def getModel(self, type: str) -> str:
        return self.getNamespace() + ":" + type + "/" + self.getFileName()
    
    def getPath(self, savePath: str, fileFormat: str) -> str:
        return path.join(savePath, self.getFileName() + path.extsep + fileFormat)
    
    def generateTexture(self, settings: Settings) -> Image:
        return generateTexture(self, settings)


class Block(Texturizeable):
    def getTexture(self) -> str:
        return super().getTexture("block")
    
    def getModel(self) -> str:
        return super().getModel("block")
    
    def generateModel(self) -> dict:
        texturePath = self.getTexture()
        return {
            "parent": "block/cube_all",
            "textures": {
                "all": texturePath,
                "particle": texturePath
            }
        }
    
    def generateBlockState(self) -> dict:
        return {
            "variants": {
                "": {
                    "model": self.getModel()
                }
            }
        }
    
    def generateItem(self) -> dict:
        return {
            "parent": self.getModel()
        }


class Item(Texturizeable):
    def getTexture(self) -> str:
        return super().getTexture("item")
    
    def getModel(self) -> str:
        return super().getModel("item")
    
    def generateModel(self) -> dict:
        return {
            "parent": "item/generated",
            "textures": {
                "layer0": self.getTexture()
            }
        }
    
    def generateItem(self) -> dict:
        return {
            "parent": "item/generated",
            "textures": {
                "layer0": self.getTexture()
            }
        }


def saveToJSON(dictionary: dict, path: str):
    with open(path, "w") as file:
        json.dump(dictionary, file, indent=2)


def makeDirs(paths: List[str]):
    for currentPath in paths:
        if (not path.exists(currentPath)):
            makedirs(currentPath)


def main():
    cprint("Running main", "green")
    
    settings: Settings
    items: List[Item] = []
    blocks: List[Block] = []

    cprint("Opening config file", "blue")
    with open("config.json", "r") as file:
        config = json.load(file)
        settings = Settings.parse(config["settings"])
        cprint("Parsing items:", "green")
        for item in config["items"]:
            cprint("Parsing item ( " + item["id"] + " )", "yellow")
            items.append(Item(item["id"], item["texture"], item["color"]))
        for block in config["blocks"]:
            cprint(f"Parsing block ( " + block["id"] + " )", "yellow")
            blocks.append(Block(block["id"], block["texture"], block["color"]))
    
    for block in blocks:
        cprint("Generating files for block ( " + block.id + " )", "red")

        itemsPath    = path.join(path.join(settings.outputPath, block.getNamespace()), "models/item")
        texturesPath = path.join(path.join(settings.outputPath, block.getNamespace()), "textures/blocks")
        statesPath   = path.join(path.join(settings.outputPath, block.getNamespace()), "blockstates")
        modelsPath   = path.join(path.join(settings.outputPath, block.getNamespace()), "models/block")

        makeDirs([itemsPath, texturesPath, statesPath, modelsPath])
        
        texture = block.generateTexture(settings)
        cprint("Saving block's texture", "blue")
        texture.save(block.getPath(texturesPath, settings.fileFormat))
        
        blockItem = block.generateItem()
        cprint("Saving block's item", "blue")
        saveToJSON(blockItem, block.getPath(itemsPath, "json"))
            

        model = block.generateModel()
        cprint("Saving block's model", "blue")
        saveToJSON(model, block.getPath(modelsPath, "json"))

        blockState = block.generateBlockState()
        cprint("Saving block's blockstate", "blue")
        saveToJSON(blockState, block.getPath(statesPath, "json"))
    
    for item in items:
        cprint("Generating files for item ( " + item.id + " )", "red")

        itemsPath    = path.join(path.join(settings.outputPath, block.getNamespace()), "models/item")
        texturesPath = path.join(path.join(settings.outputPath, block.getNamespace()), "textures/items")

        makeDirs([itemsPath, texturesPath])
        
        texture = item.generateTexture(settings)
        cprint("Saving item's texture", "blue")
        texture.save(item.getPath(texturesPath, settings.fileFormat))
        
        itemModel = item.generateItem()
        cprint("Saving item's model", "blue")
        saveToJSON(itemModel, block.getPath(itemsPath, "json"))
    
    cprint("Terminating script", "green")
    


if __name__ == "__main__":
    main()
