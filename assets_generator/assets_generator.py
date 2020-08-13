
from __future__ import annotations

import json
from os import path, makedirs
from PIL import Image, ImageDraw, ImageFont
from typing import List, Tuple

class Settings:
    def __init__(self, outputPath : str, size : int, colorMode : str, fileFormat : str, imageBackgroundColor : str, backgroundColor : str, foregroundColor : str, outlineColor : str, outlineWidth : int, fontFamily : str, fontSize : int):
        self.outputPath = outputPath
        self.size = size
        self.colorMode = colorMode
        self.fileFormat = fileFormat
        self.imageBackgroundColor = imageBackgroundColor
        self.backgroundColor = backgroundColor
        self.foregroundColor = foregroundColor
        self.outlineColor = outlineColor
        self.outlineWidth = outlineWidth
        self.fontFamily = fontFamily
        self.fontSize = fontSize

        self.font = ImageFont.truetype(self.fontFamily, self.fontSize)

    @staticmethod
    def parse(config: dict) -> Settings:
        return Settings(**config)


class NamedImage:
    def __init__(self, image: Image, name: str):
        self.image = image
        self.name = name
    
    def getPath(self, savePath : str, fileFormat : str) -> str:
        return path.join(savePath, self.name + path.extsep + fileFormat)

    def saveImage(self, savePath : str, fileFormat : str):
        self.image.save(self.getPath(savePath, fileFormat))


class Direction:
    def __init__(self, prettyName: str, direction: str, xRot: int, yRot: int, zRot: int, UVRotation: int):
        self.prettyName = prettyName
        self.direction = direction
        self.xRot = xRot
        self.yRot = yRot
        self.zRot = zRot
        self.UVRotation = UVRotation


facingDirections : List[Direction] = [
    Direction("FRONT" , "north",   0,   0, 0,   0),
    Direction("BACK"  , "south",   0, 180, 0,   0),
    Direction("RIGHT" , "east" ,   0,  90, 0,   0),
    Direction("LEFT"  , "west" ,   0, 270, 0,   0),
    Direction("TOP"   , "up"   , 270,   0, 0, 180),
    Direction("BOTTOM", "down" ,  90,   0, 0,   0)
]

booleanPropertyStates : List[str] = ["false", "true"]


def centeredText(draw: ImageDraw, font: ImageFont, x: int, y: int, text: str, color: str):
    textSize = draw.multiline_textsize(text, font)
    draw.multiline_text(
        (x - textSize[0] / 2, y - textSize[1] / 2),
        text,
        color,
        font,
        align="center"
    )

def createBaseImage(settings: Settings) -> Image:
    img = Image.new(
        settings.colorMode,
        (settings.size, settings.size),
        settings.imageBackgroundColor
    )
    
    draw = ImageDraw.Draw(img)
    draw.rectangle(
        [(0, 0), (settings.size - 1, settings.size - 1)],
        settings.backgroundColor,
        settings.outlineColor,
        settings.outlineWidth
    )

    return img


def mapNumber(value: float, valueStart: float, valueEnd: float, returnStart: float, returnEnd: float) -> float:
    if (value <= valueStart):
        return returnStart
    elif (value >= valueEnd):
        return returnEnd
    return (value - valueStart) / (valueEnd - valueStart) * (returnEnd - returnStart) + returnStart


class BlockModel:
    def __init__(self, model: dict, booleanPropertyState = False):
        self.model = model
        self.booleanPropertyState = booleanPropertyState


class Block:
    id : str
    hasFacing : bool
    hasBooleanProperty : bool
    booleanProperty : str
    booleanPropertyColor : str

    def __init__(self, blockConfig: dict):
        self.id = blockConfig["blockID"]
        self.hasFacing = blockConfig["hasFacing"]
        self.hasBooleanProperty = blockConfig["hasBooleanProperty"]
        self.booleanProperty = blockConfig["booleanProperty"]
        self.booleanPropertyColor = blockConfig["booleanPropertyColor"]
    
    def getFileName(self) -> str:
        return self.id.split(":").pop()
    
    def getNamespace(self) -> str:
        return self.id.split(":")[0]
    
    def getBlockName(self) -> str:
        blockName = self.getFileName()
        blockWords = blockName.split("_")
        for i in range(len(blockWords)):
            blockWords[i] = blockWords[i].capitalize()
        return str.join(" ", blockWords)

    def getPath(self, savePath: str, fileFormat: str) -> str:
        return path.join(savePath, self.getFileName() + path.extsep + fileFormat)
    
    def getModelPath(self) -> str:
        return self.getNamespace() + ":block/" + self.getFileName()
    
    def getTexturePath(self) -> str:
        return self.getNamespace() + ":blocks/" + self.getFileName()
    
    def generateImages(self, settings: Settings) -> List[NamedImage]:
        images = []
        for direction in facingDirections:
            facingPostfix = self.hasFacing and "_" + direction.direction.lower() or ""
            img = createBaseImage(settings)
            draw = ImageDraw.Draw(img)

            if (direction == facingDirections[0]):
                # BLOCK NAME
                centeredText(
                    draw, settings.font,
                    settings.size / 2, settings.size / 2,
                    self.getBlockName().replace(" ", "\n"), settings.foregroundColor
                )

                images.append(NamedImage(img, self.getFileName() + facingPostfix))
                if self.hasBooleanProperty:
                    img = img.copy()
                    draw = ImageDraw.Draw(img)
                    # BOOLEANPROPERTY LABEL
                    centeredText(
                        draw, settings.font,
                        settings.size / 2, settings.size - settings.outlineWidth - settings.fontSize / 2,
                        self.booleanProperty.upper(), self.booleanPropertyColor
                    )
                    images.append(NamedImage(img, self.getFileName() + facingPostfix + "_" + self.booleanProperty.lower()))
                
                if (not self.hasFacing):
                    break
            else:
                centeredText(draw, settings.font, settings.size / 2, settings.size / 2, direction.prettyName.upper(), settings.foregroundColor)
                images.append(NamedImage(img, self.getFileName() + facingPostfix))
            
        return images
    
    def generateBlockState(self) -> dict:
        blockState = {
            "variants": {}
        }

        for booleanState in (self.hasBooleanProperty and booleanPropertyStates or [""]):
            for direction in facingDirections:
                variantStates = []
                if (self.hasFacing):
                    variantStates.append("facing=" + direction.direction)
                if (self.hasBooleanProperty):
                    variantStates.append(self.booleanProperty.lower() + "=" + booleanState)
                
                currentVariant = str.join(",", variantStates)

                blockState["variants"][currentVariant] = {
                    "model": self.getModelPath() + (booleanState == "true" and ("_" + self.booleanProperty.lower()) or "")
                }

                if (not self.hasFacing):
                    break

                if (direction.xRot != 0):
                    blockState["variants"][currentVariant]["x"] = direction.xRot
                if (direction.yRot != 0):
                    blockState["variants"][currentVariant]["y"] = direction.yRot
                if (direction.zRot != 0):
                    blockState["variants"][currentVariant]["z"] = direction.zRot
            if (not self.hasBooleanProperty):
                break
        return blockState

    def generateModels(self, settings: Settings) -> List[BlockModel]:
        models = []
        for booleanState in (self.hasBooleanProperty and booleanPropertyStates or [""]):
            texturePath = self.getTexturePath() + (booleanState == "true" and ("_" + self.booleanProperty.lower()) or "")
            if (self.hasFacing):
                model = {
                    "parent": "block/block",
                    "textures": {
                        "0": texturePath,
                        "particle": texturePath
                    },
                    "elements": [
                        {
                            "from": [0, 0, 0],
                            "to": [16, 16, 16],
                            "faces": {}
                        }
                    ]
                }

                faceWidth = mapNumber(settings.size, 0, settings.size * len(facingDirections), 0, 16)
                for i in range(len(facingDirections)):

                    direction = facingDirections[i]
                    UVX = faceWidth * i

                    faces = model["elements"][0]["faces"]
                    faces[direction.direction] = {
                        "texture": "#0",
                        "uv": [UVX, 0, UVX + faceWidth, 16]
                    }

                    if (direction.UVRotation != 0):
                        faces[direction.direction]["rotation"] = direction.UVRotation
                
            else:
                model = {
                    "parent": "block/cube_all",
                    "textures": {
                        "all": texturePath
                    }
                }
            models.append(BlockModel(model, booleanState == "true"))
        
        return models
    
    def generateItemModel(self) -> dict:
        return {
            "parent": self.getModelPath()
        }


def concatenateNamedImages(namedImages: NamedImage, settings: Settings) -> Image:
    img = Image.new(settings.colorMode, (settings.size * len(namedImages), settings.size), settings.imageBackgroundColor)
    for i in range(len(namedImages)):
        namedImage = namedImages[i]
        img.paste(namedImage.image, (settings.size * i, 0))
    return img


def main():
    with open("blocks.json", mode="r") as file:
        config : dict = json.load(file)
        settings = Settings.parse(config["settings"])
        blocks : List[Block] = []

        for blockConfig in config["blocks"]:
            blocks.append(Block(blockConfig))
        
        
        
        for block in blocks:

            
            blockStatesPath =   path.join(path.join(settings.outputPath, block.getNamespace()), "blockstates")
            blockModelsPath =   path.join(path.join(settings.outputPath, block.getNamespace()), "models/block")
            blockTexturesPath = path.join(path.join(settings.outputPath, block.getNamespace()), "textures/blocks")
            blockItemsPath =    path.join(path.join(settings.outputPath, block.getNamespace()), "models/item")

            if (not path.exists(blockStatesPath)):
                makedirs(blockStatesPath)
            if (not path.exists(blockModelsPath)):
                makedirs(blockModelsPath)
            if (not path.exists(blockTexturesPath)):
                makedirs(blockTexturesPath)
            if (not path.exists(blockItemsPath)):
                makedirs(blockItemsPath)


            namedImages = block.generateImages(settings)
            if (block.hasFacing):
                if (block.hasBooleanProperty):
                    booleanPropertyTrue = namedImages.copy()
                    booleanPropertyTrue.pop(0)
                    namedImages.pop(1)
                    img = concatenateNamedImages(booleanPropertyTrue, settings)
                    img.save(path.join(blockTexturesPath, block.getFileName() + "_" + block.booleanProperty.lower()) + path.extsep + settings.fileFormat)

                img = concatenateNamedImages(namedImages, settings)
                img.save(block.getPath(blockTexturesPath, settings.fileFormat))
            else:
                for namedImage in namedImages:
                    namedImage.image.save(path.join(blockTexturesPath, namedImage.name) + path.extsep + settings.fileFormat)

            blockStatePath = path.join(blockStatesPath, block.getFileName()) + path.extsep + "json"
            with open(blockStatePath, mode="w") as blockStateFile:
                blockStateFile.write(json.dumps(block.generateBlockState(), indent=2))
            
            blockItemModel = path.join(blockItemsPath, block.getFileName()) + path.extsep + "json"
            with open(blockItemModel, mode="w") as blockItemModelFile:
                blockItemModelFile.write(json.dumps(block.generateItemModel(), indent=2))
            
            blockModels = block.generateModels(settings)
            for model in blockModels:
                modelPath = path.join(blockModelsPath, block.getFileName()) + (model.booleanPropertyState and ("_" + block.booleanProperty.lower()) or "") + path.extsep + "json"
                with open(modelPath, mode="w") as blockModelFile:
                    blockModelFile.write(json.dumps(model.model, indent=2))


if __name__ == "__main__":    
    main()
