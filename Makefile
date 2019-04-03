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
		$(B_GAME)IGame.class
	$(CC) $(FLAGS) $(SOURCE)Main.java

# --------------- Dépendences implicites ---------------------- #
#Game
$(B_GAME)Ariane.class : $(S_GAME)Ariane.java \
		$(B_GAME)IGame.class \
		$(B_GAME)Menu.class \
		$(B_TOOLS)Window.class \
		$(B_TOOLS)Background.class
	$(CC) $(FLAGS) $(S_GAME)Ariane.java

$(B_GAME)IGame.class : $(S_GAME)IGame.java
	$(CC) $(FLAGS) $(S_GAME)IGame.java

$(B_GAME)Game.class : $(S_GAME)Game.java \
		$(B_TOOLS)Read.class
	$(CC) $(FLAGS) $(S_GAME)Game.java

$(B_GAME)Menu.class : $(S_GAME)Menu.java \
		$(B_TOOLS)Background.class \
		$(B_TOOLS)MenuButtonsListener.class \
		$(B_GAME)Game.class \
		$(B_TOOLS)Write.class
	$(CC) $(FLAGS) $(S_GAME)Menu.java

#Tools
$(B_TOOLS)Window.class : $(S_TOOLS)Window.java
	$(CC) $(FLAGS) $(S_TOOLS)Window.java

$(B_TOOLS)Background.class : $(S_TOOLS)Background.java \
		$(B_TOOLS)ImageLoader.class
	$(CC) $(FLAGS) $(S_TOOLS)Background.java

$(B_TOOLS)ImageLoader.class : $(S_TOOLS)ImageLoader.java
	$(CC) $(FLAGS) $(S_TOOLS)ImageLoader.java

$(B_TOOLS)MenuButtonsListener.class : $(S_TOOLS)MenuButtonsListener.java \
		$(B_TOOLS)Background.class \
		#$(B_GAME)Menu.class
	$(CC) $(FLAGS) $(S_TOOLS)MenuButtonsListener.java

$(B_TOOLS)Read.class : $(S_TOOLS)Read.java
	$(CC) $(FLAGS) $(S_TOOLS)Read.java

$(B_TOOLS)Write.class : $(S_TOOLS)Write.java
	$(CC) $(FLAGS) $(S_TOOLS)Write.java

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