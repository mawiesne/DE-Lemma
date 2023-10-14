# DE-Lemma

![Build Status](https://github.com/mawiesne/DE-Lemma/actions/workflows/maven.yml/badge.svg)

DE-Lemma (_pronounced_: de:e: le:ma:) is an object-oriented lemmatizer for German texts with a focus on the (bio)medical domain.
                
It is based on [Apache OpenNLP](https://github.com/apache/opennlp) and provides several pre-trained, binary Maximum-Entropy _models_ in the corresponding directory. Those have been trained during October 2022 from freely available German treebanks.

## Requirements

### Build
- [Apache Maven](https://maven.apache.org) in version 3.6+

### Runtime
- Java / [OpenJDK](https://adoptium.net/de/) in version 17+
- [Apache OpenNLP](https://github.com/apache/opennlp) in version 2.1.0+ 
 
#### Notes: 
- OpenNLP releases < 2.1.0 can't reliably load the lemmatizer model files of this project! This is due to [OpenNLP-1366](https://issues.apache.org/jira/browse/OPENNLP-1366) which was detected during work for **DE-Lemma**. The bug has been fixed via [PR-427](https://github.com/apache/opennlp/pull/427) and was included in version 2.1.0. 
- Check and take care of your classpath so no older OpenNLP version is around!

## Build
Build the project via Apache Maven. 
The command for the relevant parts is `mvn clean package`.   
This should download all required dependencies which are:

1. Apache OpenNLP, 
2. Apache Commons Lang3, _and_  
3. slf4j + log4j2 bindings.

If you want to re-use the current, experimental version of **DE-Lemma** in your projects, 
execute `mvn clean install` to transport the bundled _jar_ file to your local `.m2` folder.

Note: 
You have to select one or more model files and copy it over to the execution environment.  
Those models must reside a `models` directory, as the current code checks at this directory name.
     
## Usage
For a first impression, just execute `DELemmaDemo.java` which will, by default, load the [DE-Lemma_UD-gsd-2022-maxent.bin](models%2FDE-Lemma_UD-gsd-2022-maxent.bin) 
model resource. The loaded `Lemmatizer` instance will then find the lemmas for German (non-)**inflected** nouns from the (bio)medical domain.

In the demo example, the German nouns `List.of("Ärzte", "Herzzusatztöne", ...)` will be processed. 
The results are logged to STD out / console. It should be similar to:
 
```
INFO [main] OpenNLPModelServiceImpl (OpenNLPModelServiceImpl.java:50) - Importing NLP model file 'DE-Lemma_UD-gsd-2022-maxent.bin' ...
INFO [main] DELemmaDemo (DELemmaDemo.java:30) - Found lemma 'Virus' for noun 'Viren'.
INFO [main] DELemmaDemo (DELemmaDemo.java:30) - Found lemma 'Herzzusatzton' for noun 'Herzzusatztöne'.
INFO [main] DELemmaDemo (DELemmaDemo.java:30) - Found lemma 'Vorhofflattern' for noun 'Vorhofflatterns'.
INFO [main] DELemmaDemo (DELemmaDemo.java:30) - Found lemma 'Arzt' for noun 'Ärzte'.
INFO [main] DELemmaDemo (DELemmaDemo.java:30) - Found lemma 'Klinikum' for noun 'Klinikum'.
```

## Additional technical details

... will follow soon.


## Training details

... will follow soon.
