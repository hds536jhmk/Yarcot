
# How to run

- To run this script you must have installed:
  - [Python 3.8.0](https://www.python.org/downloads/release/python-380/)
  - [Pillow (PIL) 7.2.0](https://pypi.org/project/Pillow/)

# How to use

- To change settings you can modify the file named "ores.json"
- This json object contains 2 main fields:
  - **settings** [*\<Settings>*](#settings) object (This contains all settings regarding ore textures and folders' paths)
  - **ores** *\<array>* of [*\<Ore>*](#ore) (This contains all ores that need to be generated)
- To add more ore texture you can add images to the folder specified by **settings**, you need 2 images to add a new texture:
  - **\[texture].png** : This image contains the base ore texture with or without features
  - **\[texture]_features.png** : This image contains only the features of the ore texture and should be a grayscale image

## Settings

- A *\<Settings>* object has the following fields:
  - **outputPath** *\<string>* : The folder where all the files are going to be created (*default* : `"output"`)
  - **texturesPath** *\<string>* : The folder where all textures are found (*default* : `textures`)
  - **colorMode** *\<string>* : The color mode of all ore textures (*default* : `"RGBA"`)
  - **fileFormat** *\<string>* : The file format used by all ore textures (*default* : `"png"`)

## Ore

- An *\<Ore>* object has 4 required fields:
  - **type** *\<string>* : This can be either `"block"` or `"item"`
  - **oreID** *\<string>* : The ID of the ore (e.g. "namespace:ore_name")
  - **texture** *\<int>* : This describes what texture it should be using (different texture can be found in the path `Settings.texturesPath`)
  - **color** *\<string>* : The color of the ore's features (*format* : `"#rrggbb"`, features can be found in the same path as the ore's texture and have the same name except that they have the `"_features"` postfix)
