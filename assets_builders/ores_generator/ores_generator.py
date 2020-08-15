
from __future__ import annotations
from typing import List

from os import path, makedirs
import json
from PIL import Image, ImageOps

class Settings:
    def __init__(self, outputPath: str, texturesPath: str, colorMode: str, fileFormat: str):
        self.outputPath = outputPath
        self.texturesPath = texturesPath
        self.colorMode = colorMode
        self.fileFormat = fileFormat
    
    @staticmethod
    def parse(config: dict) -> Settings:
        return Settings(**config)


class Ore:
    def __init__(self, type: str, oreID: str, texture: int, color: str):
        self.type = type
        self.id = oreID
        self.texture = texture
        self.color = color

    @staticmethod
    def parse(ore: dict) -> Ore:
        return Ore(**ore)
    
    def getFileName(self) -> str:
        return self.id.split(":").pop()
    
    def getNamespace(self) -> str:
        return self.id.split(":")[0]
    
    def getTexture(self) -> str:
        return self.getNamespace() + ":" + self.type + "s/" + self.getFileName()
    
    def getModel(self) -> str:
        return self.getNamespace() + ":" + self.type + "/" + self.getFileName()
    
    def getPath(self, savePath: str, fileFormat: str) -> str:
        return path.join(savePath, self.getFileName() + path.extsep + fileFormat)
    
    def generateTexture(self, settings: Settings) -> Image:
        texturePath = path.join(settings.texturesPath, str(self.texture))

        source = Image.open(texturePath + path.extsep + "png", "r")
        features = Image.open(texturePath + "_features" + path.extsep + "png", "r")

        _, _, _, featuresAlpha = features.split()
        colorizedFeatures = ImageOps.colorize(features.convert("L"), "#000000", self.color)
        colorizedFeatures.putalpha(featuresAlpha)

        oreTexture = Image.alpha_composite(source, colorizedFeatures)
        return oreTexture.convert(settings.colorMode)
    
    def generateModel(self) -> dict:
        texturePath = self.getTexture()
        if (self.type == "block"):
            return {
                "parent": "block/cube_all",
                "textures": {
                    "all": texturePath,
                    "particle": texturePath
                }
            }
        elif (self.type == "item"):
            return {
                "parent": "item/generated",
                "textures": {
                    "layer0": texturePath
                }
            }
    
    def generateBlockState(self) -> dict:
        if (self.type == "block"):
            return {
                "variants": {
                    "": {
                        "model": self.getModel()
                    }
                }
            }
    
    def generateItem(self) -> dict:
        if (self.type == "block"):
            return {
                "parent": self.getModel()
            }
        elif (self.type == "item"):
            return {
                "parent": "item/generated",
                "textures": {
                    "layer0": self.getTexture()
                }
            }


def saveToJSON(dictionary: dict, path: str):
    with open(path, "w") as file:
        json.dump(dictionary, file, indent=2)


def main():
    settings: Settings
    ores: List[Ore] = []
    with open("ores.json", "r") as file:
        config = json.load(file)
        settings = Settings.parse(config["settings"])
        for ore in config["ores"]:
            ores.append(Ore.parse(ore))
    
    for ore in ores:
        oreItemsPath = path.join(path.join(settings.outputPath, ore.getNamespace()), "models/item")
        if (not path.exists(oreItemsPath)):
            makedirs(oreItemsPath)
        
        oreTexturesPath = path.join(path.join(settings.outputPath, ore.getNamespace()), "textures/" + ore.type + "s")
        if (not path.exists(oreTexturesPath)):
            makedirs(oreTexturesPath)
        
        oreTexture = ore.generateTexture(settings)
        oreTexture.save(ore.getPath(oreTexturesPath, settings.fileFormat))
        
        oreItem = ore.generateItem()
        saveToJSON(oreItem, ore.getPath(oreItemsPath, "json"))
        

        if (ore.type == "block"):
            oreStatesPath   = path.join(path.join(settings.outputPath, ore.getNamespace()), "blockstates")
            oreModelsPath   = path.join(path.join(settings.outputPath, ore.getNamespace()), "models/block")

            if (not path.exists(oreStatesPath)):
                makedirs(oreStatesPath)
            if (not path.exists(oreModelsPath)):
                makedirs(oreModelsPath)
            

            oreModel = ore.generateModel()
            saveToJSON(oreModel, ore.getPath(oreModelsPath, "json"))

            oreBlockState = ore.generateBlockState()
            saveToJSON(oreBlockState, ore.getPath(oreStatesPath, "json"))


if __name__ == "__main__":
    main()
