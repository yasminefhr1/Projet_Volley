<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Modifier un Étudiant</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        fieldset {
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 20px;
            background-color: #fff;
            margin-bottom: 20px;
        }

        legend {
            font-weight: bold;
            padding: 0 10px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        input[type="submit"],
        input[type="reset"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover,
        input[type="reset"]:hover {
            background-color: #45a049;
        }

        img {
            border-radius: 5px;
        }
    </style>
</head>

<body style="margin-left: 200px;margin-right: 200px;">
    <h1>Modifier un Étudiant</h1>
    <form action="../controller/updateEtudiant.php" method="post" style="margin-left: 200px;margin-right: 200px;">
        <fieldset>
            <legend>Modifier les informations de l'étudiant</legend>
            <input type="hidden" name="id" value="<?php echo htmlspecialchars($etudiant->getId()); ?>">

            <table border="0">
                <tr>
                    <td>Nom : </td>
                    <td><input type="text" name="nom" value="<?php echo htmlspecialchars($etudiant->getNom()); ?>" required /></td>
                </tr>
                <tr>
                    <td>Prénom :</td>
                    <td><input type="text" name="prenom" value="<?php echo htmlspecialchars($etudiant->getPrenom()); ?>" required /></td>
                </tr>
                <tr>
                    <td>Ville :</td>
                    <td><input type="text" name="ville" value="<?php echo htmlspecialchars($etudiant->getVille()); ?>" required /></td>
                </tr>
                <tr>
                    <td>Sexe :</td>
                    <td>
                        M<input type="radio" name="sexe" value="homme" <?php if($etudiant->getSexe() == "homme") echo "checked"; ?> required />
                        F<input type="radio" name="sexe" value="femme" <?php if($etudiant->getSexe() == "femme") echo "checked"; ?> required />
                    </td>
                </tr>
                <tr>
                    <td>Photo URL : </td>
                    <td><input type="url" name="photo" value="<?php echo htmlspecialchars($etudiant->getPhoto()); ?>" required /></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" value="Mettre à jour" />
                        <input type="reset" value="Effacer" />
                    </td>
                </tr>
            </table>
        </fieldset>
    </form>
</body>

</html>
