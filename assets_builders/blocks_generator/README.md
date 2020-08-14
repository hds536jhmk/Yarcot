
# How to run

- To run this script you must have installed:
  - [Python 3.8.0](https://www.python.org/downloads/release/python-380/)
  - [Pillow (PIL) 7.2.0](https://pypi.org/project/Pillow/)

# How to use

- To change settings you can modify the file named "blocks.json"
- This json object contains 2 main fields:
  - **settings** [*\<Settings>*](#settings) object (This contains all settings to draw a default block image)
  - **blocks** *\<array>* of [*\<Block>*](#block) (This contains all blocks that need to be generated)

## Settings

- A *\<Settings>* object has the following fields:
  - **outputPath** *\<string>* : The folder where all the files are going to be created (*default* : `"output"`)
  - **size** *\<int>* : The resolution of the block (*default* : `128`)
  - **colorMode** *\<string>* : The color mode of the image (*default* : `"RGBA"`)
  - **fileFormat** *\<string>* : The file format used by the textures (*default* : `"png"`)
  - **imageBackgroundColor** *\<string>* : The color used by the background of all textures (*default* : `"#00000000"` *format* : `"#rrggbbaa"`)
  - **backgroundColor** *\<string>* : The color used by the block's inner square (*default* : `"#ffffffff"` *format* : `"#rrggbbaa"`)
  - **foregroundColor** *\<string>* : The color used by text (*default* : `"#000000ff"` *format* : `"#rrggbbaa"`)
  - **outlineColor** *\<string>* : The color used by the block's outline (*default* : `"#000000ff"` *format* : `"#rrggbbaa"`)
  - **outlineWidth** *\<int>* : The width of the block's outline (*default* : `8`)
  - **fontFamily** *\<string>* : The font's family (*default* : `"calibri.ttf"` *supported formats* : **TrueType**)
  - **fontSize** *\<int>* : The size of the font (*default* : `24`)

## Block

- A *\<Block>* object has 5 required fields:
  - **blockID** *\<string>* : The ID of the block (e.g. "namespace:block_name")
  - **hasFacing** *\<boolean>* : Whether or not this block has a facing (e.g. Furnaces, Chests, Barrels)
  - **hasBooleanProperty** *\<boolean>* : Whether or not this block has a *\<BooleanProperty>* (e.g. *Powered* in Furnaces, *Open* in Barrels)
  - **booleanProperty** *\<string>* : The name of the *\<BooleanProperty>* (e.g. *Powered*, *Open*)
  - **booleanPropertyColor** *\<string>* : The color of the text on the block that shows when the specified *\<BooleanProperty>* is true
- If the block doesn't have a *\<BooleanProperty>* then all related fields should be set to an empty *\<string>*
