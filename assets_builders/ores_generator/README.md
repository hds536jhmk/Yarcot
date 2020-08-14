
# How to run

- To run this script you must have installed:
  - [Python 3.8.0](https://www.python.org/downloads/release/python-380/)
  - [Pillow (PIL) 7.2.0](https://pypi.org/project/Pillow/)

# How to use

- To change settings you can modify the file named "ores.json"
- This json object contains 2 main fields:
  - **settings** [*\<Settings>*](#settings) object (This contains all settings regarding ore textures and folders' paths)
  - **ores** *\<array>* of [*\<Ore>*](#ore) (This contains all ores that need to be generated)
- To add more ore types you can add textures in the folder specified by **settings**, you need 2 textures to add a new type:
  - **\[type].png** : This image contains the base ore type texture with or without features
  - **\[type]_features.png** : This image contains only the features of the ore type and should be a grayscale image

## Settings

- A *\<Settings>* object has the following fields:
  - **outputPath** *\<string>* : The folder where all the files are going to be created (*default* : `"output"`)
  - **texturesPath** *\<string>* : The folder where all ore types textures are found (*default* : `textures`)
  - **colorMode** *\<string>* : The color mode of all textures (*default* : `"RGBA"`)
  - **fileFormat** *\<string>* : The file format used by all textures (*default* : `"png"`)

## Ore

- An *\<Ore>* object has 3 required fields:
  - **oreID** *\<string>* : The ID of the ore (e.g. "namespace:ore_name")
  - **type** *\<int>* : The type of the ore describes what texture it should be using (different types can be found in the path `Settings.texturesPath`)
  - **color** *\<string>* : The color of the ore's features (*format* : `"#rrggbb"`, features can be found in the same path as the ore's type and have the same name except that they have the `"_features"` postfix)
