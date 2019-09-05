<h3>Introduction du jeu</h3>
			<p>
				L�algorithme d�Ariane, est un cas particulier d�algorithme de guidage,
				visant � conduire un objet mobile jusqu'� son but (ici un coffre) � travers
				un parcours d'obstacles (ici des barri�res).
			</p>
			<p>
				Il est inspir� de la mythologie grec dans laquelle Th�s�e,
				cherchant � tuer le minotaure pour mettre fin � des sacrifices,
				se retrouve perdu dans son labyrinthe et cherche la sortie.
			</p>
			<p>
				Notre programme � pour but de guider Th�s�e pour qu'il sorte du labyrinthe.
				Cette version n�inclus pas le minotaure.
			</p>
		</div>
	</div>
	<div class="box-row">
		<div class="box-l">
			<h3>Pr�sentation g�n�rale</h3>
			<p>
				Le programme vous offre le choix entre choisir de cr�er un labyrinthe (en partant d'une grille
				remplie al�atoirement ou vide) ou de charger un labyrinthe existant. (voir <a href="#">SaveLoader</a> pour le format)
			</p>
			<p>
				Vous pouvez ensuite choisir comment Th�s�e va parcourir le labyrinthe :
				de fa�on d�terministe (recherche optimale)
				ou de fa�on al�atoire (choisi une direction au hasard).
			</p>
			<p>
				Enfin, vous pouvez lancer l'algorithme en mode manuel (vous verrez chaque d�placement)
				ou en mode automatique (le r�sultat du parcours est affich� directement).
			</p>
			<p>Vous pouvez trouver la totalit� des fonctionnalit�s ainsi que leurs descriptions plus d�taill�s
				dans le <a href="#rapport">rapport de projet</a>.</p>
		</div>
		<div class="box-r">
			<img src="http://[::1]/ramsamy_personnal_website/assets/img/projets/ariane/parcours.png"
				 alt="projet-Ariane: Parcours de labyrinthe" title="projet-Ariane: Parcours de labyrinthe"
				 class="realisation-img-small" onclick="showModal(this)">
		</div>
	</div>
	<div class="box-row">
		<div class="box-l">
			<img src="http://[::1]/ramsamy_personnal_website/assets/img/projets/ariane/complet.png"
				 alt="projet-Ariane: Coffre trouv�" title="projet-Ariane: Coffre trouv�"
				 class="realisation-img-small" onclick="showModal(this)">
		</div>
		<div class="box-r">
			<h3>Pr�sentation technique</h3>
			<p>
				Le programme � �t� r�alis� en <b>JAVA 8</b> en utilisant les biblioth�ques graphiques
				<b>awt</b> et <b>swing</b>.
			</p>
			<p>
				Le programme � �t� cod� en utilisant le logitiel <b>sublime texte</b> (3) pour tester les blocs
				de code. Puis, dans un second temps, re-cod� et assembl� enti�rement avec le logitiel <b>Intellij Idea 2019 (CE)</b>.
			</p>
			<p>
				Le programme compile avec un <b>Makefile</b> (src/Makefile) avec la commande <b>make</b>.
				Il se lance avec la commande <b>make run</b>.
				(ou dans Intellij Idea en appuyant sur Run)
			</p>
			<p>
				Le programme est accompagn� d'une documentation (r�dig�e en fran�ais) et d'un rapport
				de projet complet.
			</p>
		</div>