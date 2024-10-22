<?php

include_once '../racine.php';
include_once RACINE . '/service/EtudiantService.php';

$es = new EtudiantService();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $id = $_POST['id'];
    $nom = $_POST['nom'];
    $prenom = $_POST['prenom'];
    $ville = $_POST['ville'];
    $sexe = $_POST['sexe'];
    $photo = $_POST['photo'];

    $etudiant = new Etudiant($id, $nom, $prenom, $ville, $sexe, $photo);
    
    $es->update($etudiant);
    
    header('Location: ../index.php');
    exit;
}

//afficher les informations de l'étudiant pour modification
if (isset($_GET['id'])) {
    $id = $_GET['id'];
    $etudiant = $es->findById($id);

    if ($etudiant) {
        include '../update.php';
    } else {
        echo "Aucun étudiant trouvé avec cet ID.";
    }
} else {
    echo "ID manquant.";
}
?>
