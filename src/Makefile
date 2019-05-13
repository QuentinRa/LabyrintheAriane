#
# Makefile compilant à l'endroit désigné par BUILD le projet RPG
#
# Auteur : Quentin RAMSAMY--AGEORGES
# Création : 14/03/2019 (mis-à-jour le 07/04/2019)
#

#Note : Les variables commençant par B_ signifient un fichier du build (.class)
#Les variables commençant par un S_ signifient un fichier source (.java)

# compilateur
CC=javac
CL=java
# options CC et CL
CCFLAGS= -encoding UTF-8 -d $(BUILD) -implicit:none
CLFLAGS = #none

#fichier .class générés
BUILD=../build/
SOURCE=./

S_GAME=$(SOURCE)game/
S_GAMEE=$(S_GAME)evenements/
S_GAMEU=$(S_GAME)utils/

S_ENGINE=$(SOURCE)engine/
S_ENGINEA=$(S_ENGINE)algorithms/
S_ENGINEE=$(S_ENGINE)exceptions/
S_ENGINEG=$(S_ENGINE)graph/
S_ENGINEU=$(S_ENGINE)utils/
S_ENGINEW=$(S_ENGINE)window/

B_GAME=$(BUILD)game/
B_GAMEE=$(B_GAME)evenements/
B_GAMEU=$(B_GAME)utils/

B_ENGINE=$(BUILD)engine/
B_ENGINEA=$(B_ENGINE)algorithms/
B_ENGINEE=$(B_ENGINE)exceptions/
B_ENGINEG=$(B_ENGINE)graph/
B_ENGINEU=$(B_ENGINE)utils/
B_ENGINEW=$(B_ENGINE)window/


# but du makefile : produire build/source/Main.class
but: $(BUILD)Main.class

# LANCEUR DU JEU
$(BUILD)Main.class : Main.java \
	$(B_GAMEU)Game.class \
	$(B_GAME)Ariane.class
	$(CC) $(CCFLAGS) Main.java

# --------------- Dépendences implicites ---------------------- #

#engine algo
$(B_ENGINEA)Search.class : $(S_ENGINEA)Search.java \
		$(B_ENGINEG)Grille.class \
		$(B_ENGINEG)Case.class
	$(CC) $(CCFLAGS) $(S_ENGINEA)Search.java
#exceptions
$(B_ENGINEE)ErrorPopup.class : $(S_ENGINEE)ErrorPopup.java \
		$(B_ENGINEW)Window.class
	$(CC) $(CCFLAGS) $(S_ENGINEE)ErrorPopup.java

$(B_ENGINEE)InvalidDataException.class : $(S_ENGINEE)InvalidDataException.java
	$(CC) $(CCFLAGS) $(S_ENGINEE)InvalidDataException.java

$(B_ENGINEE)LoadImageException.class : $(S_ENGINEE)LoadImageException.java
	$(CC) $(CCFLAGS) $(S_ENGINEE)LoadImageException.java

$(B_ENGINEE)MessagePopup.class : $(S_ENGINEE)MessagePopup.java \
		$(B_ENGINEW)Window.class
	$(CC) $(CCFLAGS) $(S_ENGINEE)MessagePopup.java

$(B_ENGINEE)WindowException.class : $(S_ENGINEE)WindowException.java
	$(CC) $(CCFLAGS) $(S_ENGINEE)WindowException.java

$(B_ENGINEE)WinPopup.class : $(S_ENGINEE)WinPopup.java \
		$(B_ENGINEW)Window.class
	$(CC) $(CCFLAGS) $(S_ENGINEE)WinPopup.java
#graph
$(B_ENGINEG)Case.class : $(S_ENGINEG)Case.java \
		$(B_ENGINEE)InvalidDataException.class
	$(CC) $(CCFLAGS) $(S_ENGINEG)Case.java

$(B_ENGINEG)DrawGrille.class : $(S_ENGINEG)DrawGrille.java \
		$(B_ENGINEG)Grille.class \
		$(B_ENGINEG)Case.class
	$(CC) $(CCFLAGS) $(S_ENGINEG)DrawGrille.java

$(B_ENGINEG)Grille.class : $(S_ENGINEG)Grille.java \
		$(B_ENGINEE)InvalidDataException.class \
		$(B_ENGINEG)Case.class
	$(CC) $(CCFLAGS) $(S_ENGINEG)Grille.java

#utils
$(B_ENGINEU)Background.class : $(S_ENGINEU)Background.java \
		$(B_ENGINEU)ImageLoader.class
	$(CC) $(CCFLAGS) $(S_ENGINEU)Background.java

$(B_ENGINEU)CenteredPanel.class : $(S_ENGINEU)CenteredPanel.java
	$(CC) $(CCFLAGS) $(S_ENGINEU)CenteredPanel.java

$(B_ENGINEU)ImageLoader.class : $(S_ENGINEU)ImageLoader.java \
		$(B_ENGINEE)LoadImageException.class
	$(CC) $(CCFLAGS) $(S_ENGINEU)ImageLoader.java

$(B_ENGINEU)ReaderBitByBit.class : $(S_ENGINEU)ReaderBitByBit.java
	$(CC) $(CCFLAGS) $(S_ENGINEU)ReaderBitByBit.java

#window
$(B_ENGINEW)Window.class : $(S_ENGINEW)Window.java \
		$(B_ENGINEU)Background.class \
		$(B_ENGINEE)LoadImageException.class \
		$(B_ENGINEE)WindowException.class
	$(CC) $(CCFLAGS) $(S_ENGINEW)Window.java

#game events
$(B_GAMEE)CreateGameButtonsListener.class : $(S_GAMEE)CreateGameButtonsListener.java \
		$(B_ENGINEG)Grille.class \
		$(B_ENGINEG)Case.class \
		#$(B_GAMEU)CreateGame.class <-- circulaire
	$(CC) $(CCFLAGS) $(S_GAMEE)CreateGameButtonsListener.java

$(B_GAMEE)CreateGameFillButtonsListener.class : $(S_GAMEE)CreateGameFillButtonsListener.java \
		#$(B_GAMEU)CreateGame.class <-- circulaire
	$(CC) $(CCFLAGS) $(S_GAMEE)CreateGameFillButtonsListener.java

$(B_GAMEE)CreateGameListener.class : $(S_GAMEE)CreateGameListener.java \
		$(B_ENGINEG)Grille.class \
		$(B_ENGINEW)Window.class \
		$(B_ENGINEE)InvalidDataException.class \
		$(B_ENGINEE)MessagePopup.class \
		#$(B_GAME)GameCore.class <-- circulaire
	$(CC) $(CCFLAGS) $(S_GAMEE)CreateGameListener.java

$(B_GAMEE)GameCoreButtonsListener.class : $(S_GAMEE)GameCoreButtonsListener.java \
		$(B_ENGINEW)Window.class \
		#$(B_GAME)GameCore.class <-- circulaire
	$(CC) $(CCFLAGS) $(S_GAMEE)GameCoreButtonsListener.java

$(B_GAMEE)JFileChooserListener.class : $(S_GAMEE)JFileChooserListener.java \
		$(B_ENGINEW)Window.class \
		#$(B_GAME)Menu.class <-- circulaire
	$(CC) $(CCFLAGS) $(S_GAMEE)JFileChooserListener.java

$(B_GAMEE)KeyboardListener.class : $(S_GAMEE)KeyboardListener.java \
		#$(B_GAME)GameCore.class <-- circulaire
	$(CC) $(CCFLAGS) $(S_GAMEE)KeyboardListener.java

$(B_GAMEE)MenuButtonsListener.class : $(S_GAMEE)MenuButtonsListener.java \
		$(B_ENGINEW)Window.class \
		#$(B_GAME)Menu.class <-- circulaire
	$(CC) $(CCFLAGS) $(S_GAMEE)MenuButtonsListener.java

$(B_GAMEE)SaveAsListener.class : $(S_GAMEE)SaveAsListener.java \
		$(B_ENGINEE)ErrorPopup.class \
		$(B_ENGINEE)MessagePopup.class \
		$(B_ENGINEW)Window.class \
		$(B_GAMEU)WriteLoader.class \
		#$(B_GAME)GameCore.class <-- circulaire
	$(CC) $(CCFLAGS) $(S_GAMEE)SaveAsListener.java

#utils
$(B_GAMEU)CreateGame.class : $(S_GAMEU)CreateGame.java \
		$(B_ENGINEG)Grille.class \
		$(B_ENGINEG)Case.class \
		$(B_ENGINEG)DrawGrille.class \
		$(B_ENGINEE)ErrorPopup.class \
		$(B_ENGINEW)Window.class \
		$(B_GAMEE)CreateGameButtonsListener.class \
		$(B_GAMEE)CreateGameFillButtonsListener.class \
		$(B_GAMEE)CreateGameListener.class \
		$(B_GAMEE)SaveAsListener.class \
		#$(B_GAME)GameCore.class <-- circulaire
	$(CC) $(CCFLAGS) $(S_GAMEU)CreateGame.java

$(B_GAMEU)Game.class : $(S_GAMEU)Game.java \
		$(B_ENGINEE)WindowException.class
	$(CC) $(CCFLAGS) $(S_GAMEU)Game.java

$(B_GAMEU)GameComponent.class : $(S_GAMEU)GameComponent.java
	$(CC) $(CCFLAGS) $(S_GAMEU)GameComponent.java

$(B_GAMEU)SaveLoader.class : $(S_GAMEU)SaveLoader.java \
		$(B_ENGINEG)Grille.class \
		$(B_ENGINEG)Case.class \
		$(B_ENGINEE)ErrorPopup.class \
		$(B_ENGINEE)InvalidDataException.class \
		$(B_ENGINEU)ReaderBitByBit.class
	$(CC) $(CCFLAGS) $(S_GAMEU)SaveLoader.java

$(B_GAMEU)WriteLoader.class : $(S_GAMEU)WriteLoader.java \
		$(B_ENGINEG)Grille.class \
		$(B_ENGINEG)Case.class
	$(CC) $(CCFLAGS) $(S_GAMEU)WriteLoader.java

#game
$(B_GAME)Ariane.class : $(S_GAME)Ariane.java \
		$(B_ENGINEE)WindowException.class \
		$(B_ENGINEW)Window.class \
		$(B_GAME)Menu.class \
		$(B_GAMEU)Game.class
	$(CC) $(CCFLAGS) $(S_GAME)Ariane.java

$(B_GAME)GameCore.class : $(S_GAME)GameCore.java \
		$(B_ENGINEA)Search.class \
		$(B_ENGINEG)Grille.class \
		$(B_ENGINEG)Case.class \
		$(B_ENGINEG)DrawGrille.class \
		$(B_ENGINEW)Window.class \
		$(B_ENGINEE)ErrorPopup.class \
		$(B_ENGINEE)WinPopup.class \
		$(B_ENGINEU)CenteredPanel.class \
		$(B_GAMEE)CreateGameListener.class \
		$(B_GAMEU)CreateGame.class \
		$(B_GAMEU)SaveLoader.class \
		$(B_GAMEE)GameCoreButtonsListener.class \
		$(B_GAMEE)KeyboardListener.class \
		$(B_GAMEU)GameComponent.class
	$(CC) $(CCFLAGS) $(S_GAME)GameCore.java

$(B_GAME)Menu.class : $(S_GAME)Menu.java \
		$(B_GAME)GameCore.class \
		$(B_ENGINEW)Window.class \
		$(B_ENGINEU)CenteredPanel.class \
		$(B_ENGINEE)ErrorPopup.class \
		$(B_GAMEE)MenuButtonsListener.class \
		$(B_GAMEE)JFileChooserListener.class \
		$(B_GAMEU)GameComponent.class
	$(CC) $(CCFLAGS) $(S_GAME)Menu.java

# --------------------- BUTS FACTICES ------------------------- #

run:
	cd $(BUILD) && $(CL) $(CLFLAGS) Main && cd ../src

clean:
	`cd ..;find -name "*.class" -exec rm -rf {} \;; cd src/;`

javadoc:
	javadoc -d $(JAVADOC) -version -author -html5 \
		src.engine src.engine.algorithms src.engine.exceptions \
		src.engine.graph src.engine.utils src.engine.window \
		src.game src.game.evenements src.game.utils

doc:
	`firefox -private -new-tab "build/javadoc/index.html"`

#but factices - but est déja factice ...
.PHONY : but \
		 run \
		 doc \
		 clean