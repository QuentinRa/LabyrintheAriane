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
# options CC et CL
CCFLAGS= -encoding UTF-8 -d $(BUILD) -implicit:none
CLFLAGS = #none

#Chemins principaux
#fichiers source
SOURCE=source/
#fichier .class générés
BUILD=build/
#documentation
JAVADOC=$(BUILD)javadoc/

#Variables pour les chemins des fichiers source (S)
S_GAME=$(SOURCE)game/
S_GAMEI=$(S_GAME)interfaces/
S_TOOLS=$(SOURCE)tools/
S_TOOLSU=$(S_TOOLS)utils/
S_TOOLSEV=$(S_TOOLS)events/
S_TOOLSEX=$(S_TOOLS)exceptions/

#Variables pour les chemins des fichiers build (B)
B_SOURCE=$(BUILD)$(SOURCE)

B_GAME=$(B_SOURCE)game/
B_GAMEI=$(B_GAME)interfaces/
B_TOOLS=$(B_SOURCE)tools/
B_TOOLSU=$(B_TOOLS)utils/
B_TOOLSEV=$(B_TOOLS)events/
B_TOOLSEX=$(B_TOOLS)exceptions/

# but du makefile : produire build/source/Main.class
but: $(B_SOURCE)Main.class

# LANCEUR DU JEU
$(B_SOURCE)Main.class : $(SOURCE)Main.java \
		$(B_GAME)Ariane.class \
		$(B_GAMEI)IGame.class
	$(CC) $(CCFLAGS) $(SOURCE)Main.java

# --------------- Dépendences implicites ---------------------- #
#Package game
$(B_GAME)Ariane.class : $(S_GAME)Ariane.java \
		$(B_GAMEI)IGame.class \
		$(B_TOOLS)Window.class \
		$(B_GAMEI)IGameComponent.class \
		$(B_GAME)Menu.class
	$(CC) $(CCFLAGS) $(S_GAME)Ariane.java

$(B_GAME)Menu.class : $(S_GAME)Menu.java \
		$(B_GAMEI)IGameComponent.class \
		$(B_TOOLSEV)MenuButtons.class \
		$(B_GAME)MainGame.class \
		$(B_TOOLS)Window.class
	$(CC) $(CCFLAGS) $(S_GAME)Menu.java

$(B_GAME)MainGame.class : $(S_GAME)MainGame.java \
		$(B_GAMEI)IGameComponent.class \
		$(B_TOOLS)Window.class \
		$(B_GAME)Grille.class \
		$(B_TOOLSU)SaveLoader.class \
		$(B_TOOLS)DrawGrille.class \
		$(B_GAME)Cases.class \
		$(B_TOOLS)Graphes.class
	$(CC) $(CCFLAGS) $(S_GAME)MainGame.java

$(B_GAME)Grille.class : $(S_GAME)Grille.java \
		$(B_TOOLSEX)InvalidDataException.class \
		$(B_GAME)Cases.class
	$(CC) $(CCFLAGS) $(S_GAME)Grille.java

$(B_GAME)Cases.class : $(S_GAME)Cases.java \
		$(B_TOOLSEX)InvalidDataException.class
	$(CC) $(CCFLAGS) $(S_GAME)Cases.java

#Package interfaces
$(B_GAMEI)IGame.class : $(S_GAMEI)IGame.java
	$(CC) $(CCFLAGS) $(S_GAMEI)IGame.java

$(B_GAMEI)IGameComponent.class : $(S_GAMEI)IGameComponent.java
	$(CC) $(CCFLAGS) $(S_GAMEI)IGameComponent.java

#Package tools
$(B_TOOLS)Window.class : $(S_TOOLS)Window.java \
		$(B_TOOLSU)Background.class
	$(CC) $(CCFLAGS) $(S_TOOLS)Window.java

$(B_TOOLS)DrawGrille.class : $(S_TOOLS)DrawGrille.java \
		$(B_GAME)Grille.class \
		$(B_TOOLSEX)InvalidDataException.class \
		$(B_GAME)Cases.class
	$(CC) $(CCFLAGS) $(S_TOOLS)DrawGrille.java

$(B_TOOLS)Graphes.class : $(S_TOOLS)Graphes.java \
		$(B_TOOLSEX)InvalidDataException.class \
		$(B_GAME)Grille.class \
		$(B_GAME)Cases.class
	$(CC) $(CCFLAGS) $(S_TOOLS)Graphes.java

#Package events
$(B_TOOLSEV)MenuButtons.class : $(S_TOOLSEV)MenuButtons.java \
		$(B_TOOLS)Window.class \
		#$(B_GAME)Menu.class	<-- circulaire
	$(CC) $(CCFLAGS) $(S_TOOLSEV)MenuButtons.java

#Packtage utils
$(B_TOOLSU)Background.class : $(S_TOOLSU)Background.java \
		$(B_TOOLSU)ImageLoader.class
	$(CC) $(CCFLAGS) $(S_TOOLSU)Background.java

$(B_TOOLSU)ImageLoader.class : $(S_TOOLSU)ImageLoader.java
	$(CC) $(CCFLAGS) $(S_TOOLSU)ImageLoader.java

$(B_TOOLSU)SaveLoader.class : $(S_TOOLSU)SaveLoader.java \
		$(B_TOOLSU)ReaderBitByBit.class \
		$(B_TOOLSEX)InvalidDataException.class \
		$(B_GAME)Cases.class \
		$(B_TOOLS)Window.class \
		$(B_TOOLSU)ErrorPopup.class
	$(CC) $(CCFLAGS) $(S_TOOLSU)SaveLoader.java

$(B_TOOLSU)ReaderBitByBit.class : $(S_TOOLSU)ReaderBitByBit.java
	$(CC) $(CCFLAGS) $(S_TOOLSU)ReaderBitByBit.java

$(B_TOOLSU)ErrorPopup.class : $(S_TOOLSU)ErrorPopup.java
	$(CC) $(CCFLAGS) $(S_TOOLSU)ErrorPopup.java

#Packtage exceptions
$(B_TOOLSEX)InvalidDataException.class : $(S_TOOLSEX)InvalidDataException.java
	$(CC) $(CCFLAGS) $(S_TOOLSEX)InvalidDataException.java

# --------------------- BUTS FACTICES ------------------------- #

run:
	cd build/ && $(CL) $(CLFLAGS) source.Main && cd ..

clean:
	`find -name "*.class" -exec rm -rf {} \;; rm -rf $(JAVADOC)*;`

javadoc:
	javadoc -d $(JAVADOC) -version -author -html5 \
		source.game source.game.interfaces \
		source.tools source.tools.utils source.tools.events \
		source

doc:
	`firefox -private -new-tab "build/javadoc/index.html"`

#but factices - but est déja factice ...
.PHONY : but \
		 run \
		 doc \
		 clean