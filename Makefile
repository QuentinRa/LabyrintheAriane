#
# Makefile compilant à l'endroit désigné par BUILD le projet RPG
#
# Auteur : Quentin RAMSAMY--AGEORGES
# Création : 14/03/2019 (mise-à-jour le 01/04/2019)
#

#Note : Les variables commençant par B_ signifient un fichier du build
#Les variables commençant par un S_ signifient un fichier source

# compilateur
CC=javac
CL=java
# options
FLAGS= -encoding UTF-8 -d $(BUILD) -implicit:none
CLFLAGS = 

#Chemins principaux
#fichiers source
SOURCE=source/
#fichier .class générés
BUILD=build/

#Variables pour les chemins des fichiers source (S)
S_GAME=$(SOURCE)game/
S_TOOLS=$(SOURCE)tools/
S_TOOLSL=$(S_TOOLS)listeners/
S_TOOLSS=$(S_TOOLS)streams/

#Variables pour les chemins des fichiers build (B)
B_SOURCE=$(BUILD)$(SOURCE)

B_GAME=$(B_SOURCE)game/
B_TOOLS=$(B_SOURCE)tools/
B_TOOLSL=$(B_TOOLS)listeners/
B_TOOLSS=$(B_TOOLS)streams/

# but du makefile : produire build/source/Main.class
but: $(B_SOURCE)Main.class

# LANCEUR DU JEU
$(B_SOURCE)Main.class : $(SOURCE)Main.java \
		$(B_GAME)Ariane.class \
		$(B_GAME)IGame.class \
		$(B_TOOLSL)KeyboardListener.class
	$(CC) $(FLAGS) $(SOURCE)Main.java

# --------------- Dépendences implicites ---------------------- #
#Game
$(B_GAME)Ariane.class : $(S_GAME)Ariane.java \
		$(B_GAME)IGame.class \
		$(B_TOOLS)Window.class
	$(CC) $(FLAGS) $(S_GAME)Ariane.java

$(B_GAME)IGame.class : $(S_GAME)IGame.java
	$(CC) $(FLAGS) $(S_GAME)IGame.java

#Tools
$(B_TOOLS)Window.class : $(S_TOOLS)Window.java \
		$(B_TOOLSS)DrawImage.class
	$(CC) $(FLAGS) $(S_TOOLS)Window.java
#Listeners
$(B_TOOLSL)KeyboardListener.class : $(S_TOOLSL)KeyboardListener.java
	$(CC) $(FLAGS) $(S_TOOLSL)KeyboardListener.java
#steams
$(B_TOOLSS)DrawImage.class : $(S_TOOLSS)DrawImage.java \
		$(B_TOOLSS)ImageLoader.class
	$(CC) $(FLAGS) $(S_TOOLSS)DrawImage.java

$(B_TOOLSS)ImageLoader.class : $(S_TOOLSS)ImageLoader.java
	$(CC) $(FLAGS) $(S_TOOLSS)ImageLoader.java

# --------------------- BUTS FACTICES ------------------------- #

run:
	cd build/ && $(CL) $(CLFLAGS) source.Main && cd ..

clean:
	`find -name "*.class" -exec rm -rf {} \;`

javadoc:
	javadoc -d build/javadoc/ \
		source.game \
		source.tools \
		source

doc:
	`firefox -private -new-tab "build/javadoc/index.html"`

#but factices
.PHONY : run \
		 doc \
		 clean