# SimpleOOPEShell

The program was an assignment at an object-oriented Java programming course at the university. SimpleOOPEShell simulates a textual user interface that takes commands from the user and processes files and folders accordingly. The program uses Linked List as the data structure for the folders and the data is stored in RAM.

## Examples

md <somefile> command creates new folder of given name. ls lists the contents of the current folder.

```
Welcome to SOS.
/>md pics
/>cd pics
/pics/>md cats
/pics/>md dogs
/pics/>ls
cats/ 0
dogs/ 0
```

cd <somefolder in the current directory> command changes directory.

```
/pics/>cd cats
```

mf <somefilename and file size> creates new file in the current folder. Size is a compulsory parameter and omiting it produces error. 

```
/pics/cats/>mf kitten.jpg 12
/pics/cats/>mf ggrey_cat.jpg
Error!
/pics/cats/>mf ggrey_cat.jpg 15
```

mv <oldname> <newname> renames the file with the new name.

```
/pics/cats/>mv ggrey_cat.jpg grey_cat.jpg
/pics/cats/>ls
grey_cat.jpg 15
kitten.jpg 12
```

cp <filename> <copyname> copies the given file with a new name. 

```
/pics/cats/>cp kitten.jpg kitten_backup.jpg
/pics/cats/>ls
grey_cat.jpg 15
kitten.jpg 12
kitten_backup.jpg 12
```

rm <filename> removes the file with the given name. 

```
/pics/cats/>rm kitten.jpg
/pics/cats/>ls
grey_cat.jpg 15
kitten_backup.jpg 12
```

Unknown command produces an error.

```
/pics/cats/>--           
Error!
/pics/cats/>..
Error!
/pics/cats/>cd
```

find lists folders and their contents recursively in preorder.


```
/>find
/pics/ 2
/pics/cats/ 2
/pics/cats/grey_cat.jpg 15
/pics/cats/kitten_backup.jpg 12
/pics/dogs/ 0
```

exit ends the program.


```
/>exit
Shell terminated.

```

## Running

```
mvn exec:java -Dexec.mainClass=oope2017ht.Oope2017HT
```

## License

This project is licensed under the MIT License - see the [LICENSE.txt](LICENSE.txt) file for details

## Acknowledgments

apulaiset and fi/uta/csjola/oope/lista folders were provided by our teacher Jorma Laurikkala. fi/uta/csjola/oope/lista contains Linked List. apulaiset contains In.java which is a helper class that uses Scanner to read input from the user. Komennettava.java and Ooperoiva.java are interfaces to be used by the interpreter and the Linked List.
