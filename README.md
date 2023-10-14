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
Those models must reside in the `models` directory, as the current code inspects this directory name.
     
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

## How to obtain all German model files?
The complete set of files consists of four models:

| Model name                      | Size | External download required                                                             |
|---------------------------------|------|----------------------------------------------------------------------------------------|
| DE-Lemma_UD-gsd-2022-maxent.bin | 861K | No                                                                                     |
| DE-Lemma_UD-hdt-2022-maxent.bin | 14M  | [Yes](https://download.it.hs-heilbronn.de/de-lemma/DE-Lemma_UD-hdt-2022-maxent.bin)    |
| DE-Lemma_Tue-BuReg-2022-maxent.bin | 3.9M | [Yes](https://download.it.hs-heilbronn.de/de-lemma/DE-Lemma_Tue-BuReg-2022-maxent.bin) |
| DE-Lemma_Tue-Wiki-2022-maxent.bin | 131M | [Yes](https://download.it.hs-heilbronn.de/de-lemma/DE-Lemma_Tue-Wiki-2022-maxent.bin)  |

as reported in the paper. All trained models were evaluated for lemma prediction performance, see **Table 3** in the paper.

If you clone this repository, only the _DE-Lemma_UD-gsd-2022-maxent.bin_ model will be included in the `models`
directory of this Git repository. For reasons of limited storage, you'll have to download all other
[model files](https://download.it.hs-heilbronn.de/de-lemma/)  separately. Once retrieved, place those model files in the `models` directory to start experimenting
with it.

## How to cite?
If you use **DE-Lemma** models or the lemmatizer code in scientific work, please cite the 
[GMDS 2023](https://www.gmds2023.de/proceedings) paper as follows:

Wiesner M. _DE-Lemma: A Maximum-Entropy Based Lemmatizer for German Medical Text._ 
Studies in Health Technology and Informatics. 2023 Sep 12;**307**:189-195. 
DOI: [10.3233/SHTI230712](https://doi.org/10.3233/SHTI230712), 
PMID: [37697853](https://www.ncbi.nlm.nih.gov/pubmed/37697853)

## Training details
Several available treebanks (in _CoNLL-U_ or _CoNLL-X_ format) were identified
and selected as candidates for training German lemmatizer models.

The German UD-treebanks, [UD-GSD and UD-HDT](https://universaldependencies.org/treebanks/de-comparison.html), are constructed from
text corpora of German newspapers and other freely available text materials.
The treebanks [TüBa-D/DP](https://uni-tuebingen.de/fakultaeten/philosophische-fakultaet/fachbereiche/neuphilologie/seminar-fuer-sprachwissenschaft/arbeitsbereiche/allg-sprachwissenschaft-computerlinguistik/ressourcen/corpora/tueba-ddp/) 
and [TüBa-D/W](https://uni-tuebingen.de/fakultaeten/philosophische-fakultaet/fachbereiche/neuphilologie/seminar-fuer-sprachwissenschaft/arbeitsbereiche/allg-sprachwissenschaft-computerlinguistik/ressourcen/corpora/tueba-dw/) 
also qualified for training lemmatizer models. Those contain information about word types, morphology, lemmas, as well as dependency relations. 
TüBa-D/W represents a huge corpus: It is based on Wikipedia text material including 36.1 million sentences.

The training of lemmatizer models was conducted based on the open-source NLP toolkit [Apache OpenNLP](https://opennlp.apache.org).
For the generation of lemmatizer models with smaller treebanks (UD-GSD, UDHDT, TüBa-D/DP-political), 
the OpenNLP training parameters were chosen as follows:

```
training.algorithm=maxent 
training.iterations=100 
training.cutoff=5
training.threads=16 
language=de 
use.token.end=false
sentences.per.sample=5 
upos.tagset=upos
```
    
The training for TüBa-D/W was conducted with these parameters:
```
training.algorithm=maxent
training.iterations=20 
training.cutoff=5
training.threads=4 
language=de 
use.token.end=false
sentences.per.sample=5
upos.tagset=upos
```

Since the training of a lemmatizer model (LM) required between ~32 GB (UD-GSD) and
~1,100 GB (TüBa-D/W) of RAM at runtime, these tasks could not be performed on
conventional workstation hardware. Therefore, the training of each model was conducted
on the mainframe environment of the [bwUniCluster](https://wiki.bwhpc.de/e/BwUniCluster2.0) during October 2022.
The execution environment of the training program was a Java Runtime
Environment (JRE), a 64bit OpenJDK in version 8 build 292.

The resulting binary model files were persisted for evaluation and later re-use in NLP
applications with a lemmatizer component.