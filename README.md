# Lucene-Application-Getting-scores
Pour la récupération des indexes, on éxucute la classe LuceneTester.java





1/ pour la récupération du score thématique par lucene: on éxécute la classe Searcher.java
   ( TopDocs hits = searcher.search(q, null, 1000, sort);) la ligne 93 on la laisse tel qu'elle est.
   
2/ pur la récupération du score de notre approche mise au niveau de la classe InfluenceBoosting, on mets q2 au lieu de q au niveau de la ligne 93 de la classe Searcher.
    ( TopDocs hits = searcher.search(q2, null, 1000, sort);)
    
3/ pour la récupération des données à partir de twitter on utilise deux méthodes :

a/ à partir du cache de l'indexation
b/ à partir d'une classe externe : TESTE.java

dans la classe InfluenceBoosting, on illustre les 2.
