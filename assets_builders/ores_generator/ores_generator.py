
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
    def __init__(self, oreID: str, type: int, color: str):
        self.id = oreID
        self.type = type
        self.color = color

    @staticmethod
    def parse(ore: dict) -> Ore:
        return Ore(**ore)
    
    def getFileName(self) -> str:
        return self.id.split(":").pop()
    
    def getNamespace(self) -> str:
        return self.id.split(":")[0]
    
    def getTexture(self) -> str:
        return self.getNamespace() + ":blocks/" + self.getFileName()
    
    def getModel(self) -> str:
        return self.getNamespace() + ":block/" + self.getFileName()
    
    def getPath(self, savePath: str, fileFormat: str) -> str:
        return path.join(savePath, self.getFileName() + path.extsep + fileFormat)
    
    def generateTexture(self, settings: Settings) -> Image:
        typePath = path.join(settings.texturesPath, str(self.type))

        source = Image.open(typePath + path.extsep + "png", "r")
        features = Image.open(typePath + "_features" + path.extsep + "png", "r")

        _, _, _, featuresAlpha = features.split()
        colorizedFeatures = ImageOps.colorize(features.convert("L"), "#000000", self.color)
        colorizedFeatures.putalpha(featuresAlpha)

        oreTexture = Image.alpha_composite(source, colorizedFeatures)
        return oreTexture.convert(settings.colorMode)
    
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
        oreStatesPath   = path.join(path.join(settings.outputPath, ore.getNamespace()), "blockstates")
        oreModelsPath   = path.join(path.join(settings.outputPath, ore.getNamespace()), "models/block")
        oreTexturesPath = path.join(path.join(settings.outputPath, ore.getNamespace()), "textures/blocks")
        oreItemsPath    = path.join(path.join(settings.outputPath, ore.getNamespace()), "models/item")

        if (not path.exists(oreStatesPath)):
            makedirs(oreStatesPath)
        if (not path.exists(oreModelsPath)):
            makedirs(oreModelsPath)
        if (not path.exists(oreTexturesPath)):
            makedirs(oreTexturesPath)
        if (not path.exists(oreItemsPath)):
            makedirs(oreItemsPath)
        
        oreTexture = ore.generateTexture(settings)
        oreTexture.save(ore.getPath(oreTexturesPath, settings.fileFormat))

        oreModel = ore.generateModel()
        saveToJSON(oreModel, ore.getPath(oreModelsPath, "json"))

        oreBlockState = ore.generateBlockState()
        saveToJSON(oreBlockState, ore.getPath(oreStatesPath, "json"))
        
        oreItem = ore.generateItem()
        saveToJSON(oreItem, ore.getPath(oreItemsPath, "json"))
        


if __name__ == "__main__":
    main()
