<?php

include_once '../racine.php';
include_once RACINE . '/service/EtudiantService.php';


header("Content-Type: application/json"); // Set content type to JSON

$es = new EtudiantService();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Get the raw POST data
    $data = json_decode(file_get_contents("php://input"), true);

    // Validate the incoming data
    if (isset($data['id'], $data['nom'], $data['prenom'], $data['ville'], $data['sexe'], $data['photo'])) {
        $id = $data['id'];
        $nom = $data['nom'];
        $prenom = $data['prenom'];
        $ville = $data['ville'];
        $sexe = $data['sexe'];
        $photo = $data['photo'];

        // Create a new Etudiant object
        $etudiant = new Etudiant($id, $nom, $prenom, $ville, $sexe, $photo);

        // Call the update method
        if ($es->update($etudiant)) {
            // Successful update
            echo json_encode(["success" => true, "message" => "Étudiant mis à jour avec succès."]);
        } else {
            // Update failed
            echo json_encode(["success" => false, "message" => "Échec de la mise à jour de l'étudiant."]);
        }
    } else {
        $missingFields = [];
        if (!isset($data['id'])) $missingFields[] = 'id';
        if (!isset($data['nom'])) $missingFields[] = 'nom';
        if (!isset($data['prenom'])) $missingFields[] = 'prenom';
        if (!isset($data['ville'])) $missingFields[] = 'ville';
        if (!isset($data['sexe'])) $missingFields[] = 'sexe';
        if (!isset($data['photo'])) $missingFields[] = 'photo';
    
        echo json_encode(["success" => false, "message" => "Données d'entrée invalides : " . implode(', ', $missingFields)]);
    }
    
} else {
    // Method not allowed
    http_response_code(405); // Method Not Allowed
    echo json_encode(["success" => false, "message" => "Méthode non autorisée."]);
}
?>
