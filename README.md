<h1>La javadoc est disponible à l'adresse : <a href="https://ariane-javadoc.000webhostapp.com/">Javadoc</a></h1>

<h3>Introduction du jeu</h3>
			<p>
				L’algorithme d’Ariane, est un cas particulier d’algorithme de guidage,
				visant à conduire un objet mobile jusqu'à son but (ici un coffre) à travers
				un parcours d'obstacles (ici des barrières).
			</p>
			<p>
				Il est inspiré de la mythologie grec dans laquelle Thésée,
				cherchant à tuer le minotaure pour mettre fin à des sacrifices,
				se retrouve perdu dans son labyrinthe et cherche la sortie.
			</p>
			<p>
				Notre programme à pour but de guider Thésée pour qu'il sorte du labyrinthe.
				Cette version n’inclus pas le minotaure.
			</p>
		</div>
	</div>
	<div class="box-row">
		<div class="box-l">
			<h3>Présentation générale</h3>
			<p>
				Le programme vous offre le choix entre choisir de créer un labyrinthe (en partant d'une grille
				remplie aléatoirement ou vide) ou de charger un labyrinthe existant. (voir <a href="https://ariane-javadoc.000webhostapp.com/game/utils/SaveLoader.html">SaveLoader</a> pour le format)
			</p>
			<p>
				Vous pouvez ensuite choisir comment Thésée va parcourir le labyrinthe :
				de façon déterministe (recherche optimale)
				ou de façon aléatoire (choisi une direction au hasard).
			</p>
			<p>
				Enfin, vous pouvez lancer l'algorithme en mode manuel (vous verrez chaque déplacement)
				ou en mode automatique (le résultat du parcours est affiché directement).
			</p>
			<p>Vous pouvez trouver la totalité des fonctionnalités ainsi que leurs descriptions plus détaillés
				dans le 
				<b>rapport de projet</b>.
			</p>
		</div>
		<div class="box-r">
			<h3>Présentation technique</h3>
			<p>
				Le programme à été réalisé en <b>JAVA 8</b> en utilisant les bibliothèques graphiques
				<b>awt</b> et <b>swing</b>.
			</p>
			<p>
				Le programme à été codé en utilisant le logitiel <b>sublime texte</b> (3) pour tester les blocs
				de code. Puis, dans un second temps, re-codé et assemblé entièrement avec le logitiel <b>Intellij Idea 2019 (CE)</b>.
			</p>
			<p>
				Le programme compile avec un <b>Makefile</b> (src/Makefile) avec la commande <b>make</b>.
				Il se lance avec la commande <b>make run</b>.
				(ou dans Intellij Idea en appuyant sur Run)
			</p>
			<p>
				Le programme est accompagné d'une documentation (rédigée en français) et d'un rapport
				de projet complet.
			</p>
		</div>

