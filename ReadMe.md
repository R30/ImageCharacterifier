# ImageCharacterifier
So you have an image, and you want to turn it into a collection of characters?
### Then ImageCharacterifier is something for YOU!
###### You can turn an image in various formats, ranging from .png and .jpg to .svg into a beautiful .txt file of characters representing your image!

Additionally it is very customizable. For example, you can change the width of the image, the characters from which it exists and adjust the 'brightness' of the result.

Take a look at the examples, included in this repo!

## So, how do I use it?
Compile all `.java` files, then run `src.ImageCharacterisationExample` with an argument with the URL of the image. For example,
```
java ImageCharacterisationExample https://upload.wikimedia.org/wikipedia/commons/thumb/6/61/Artaxiad_coat_of_arms_by_PeopleOfAr.svg/800px-Artaxiad_coat_of_arms_by_PeopleOfAr.svg.png > ArtaxiadFlag.txt
```
will return output the Artaxiad flag from the link to a file titled `ArtaxiadFlag.txt`

Of course, `src.ImageCharacterisationExample` only demonstrates an example usage, and if you have a `BufferedImage` of your image you can simply use the `src.Image_characterifier` class to characterify to your wishes!