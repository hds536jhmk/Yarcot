
# How to run

- To run this script you must have installed:
  - [Python 3.8.0](https://www.python.org/downloads/release/python-380/)
  - [Pillow (PIL) 7.2.0](https://pypi.org/project/Pillow/)
  - [Termcolor 1.1.0](https://pypi.org/project/termcolor/)

# How to use

- To change settings you can modify the file named "config.json"
- This json object contains 3 main fields:
  - **settings** [*\<Settings>*](#settings) object (This contains all settings regarding textures and folders' paths)
  - **items** *\<array>* of [*\<Item>*](#item-block) (This contains all items that need to be generated)
  - **blocks** *\<array>* of [*\<Block>*](#item-block) (This contains all blocks that need to be generated)
- To add more texture templates you can add images to the folder specified by **settings**, you need at least 1 image to add a new template:
  - **\[texture]_base.png** : This image isn't required, this is the image where the grayscale is put onto (If it doesn't exist then the script creates a new transparent image of the same size as the grayscale)
  - **\[texture]_grayscale_.png** : This image MUST be provided, this is the image that gets colorized and then overlapped with the base image

## Settings

- A *\<Settings>* object has the following fields:
  - **outputPath** *\<string>* : The folder where all the files are going to be created (*default* : `"output"`)
  - **texturesPath** *\<string>* : The folder where all texture templates are found (*default* : `templates`)
  - **colorMode** *\<string>* : The color mode of all generated textures (*default* : `"RGBA"`)
  - **fileFormat** *\<string>* : The file format used by all generated textures (*default* : `"png"`)

## Item/Block

- An *\<Item>*/*\<Block>* object has 3 required fields:
  - **id** *\<string>* : The ID of the Item/Block (e.g. `"namespace:id"`)
  - **texture** *\<string>* : This describes what texture template it should be using (different texture templates can be found in the path `Settings.texturesPath`)
  - **color** *\<string>* : The color that is used to colorize the grayscale texture (*format* : `"#rrggbb"`, grayscales can be found in the same path as the texture and have the same name except that they have the `"_grayscale"` postfix istead of the `"_base"` one)
