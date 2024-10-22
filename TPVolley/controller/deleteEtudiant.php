<?php

include_once '../racine.php';
include_once RACINE . '/service/EtudiantService.php';

if (isset($_GET['id'])) {
    $id = intval($_GET['id']); 
    $es = new EtudiantService();

    $etudiant = $es->findById($id);}
