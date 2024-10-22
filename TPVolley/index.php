<!DOCTYPE html>
<?php include_once './racine.php'; ?>
<html>

<head>
    <meta charset="UTF-8">
    <title>Ajouter un Étudiant</title>
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
    <h1>Ajouter un Nouveau Étudiant</h1>
    <form method="GET" action="controller/addEtudiant.php" style="margin-left: 200px;margin-right: 200px;">
        <fieldset>
            <legend>Ajouter un nouveau étudiant</legend>
            <table border="0">
                <tr>
                    <td>Nom : </td>
                    <td><input type="text" name="nom" required /></td>
                </tr>
                <tr>
                    <td>Prenom :</td>
                    <td><input type="text" name="prenom" required /></td>
                </tr>
                <tr>
                    <td>Ville</td>
                    <td>
                        <select name="ville" required>
                            <option value="Marrakech">Marrakech</option>
                            <option value="Rabat">Rabat</option>
                            <option value="Agadir">Agadir</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Sexe </td>
                    <td>
                        M<input type="radio" name="sexe" value="homme" required />
                        F<input type="radio" name="sexe" value="femme" required />
                    </td>
                </tr>
                <tr>
                    <td>Photo URL : </td>
                    <td><input type="url" name="photo" required /></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" value="Envoyer" />
                        <input type="reset" value="Effacer" />
                    </td>
                </tr>
            </table>
        </fieldset>
    </form>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Prenom</th>
                <th>Ville</th>
                <th>Sexe</th>
                <th>Photo URL</th>
                <th>Supprimer</th>
                <th>Modifier</th>
            </tr>
        </thead>
        <tbody>
            <?php
            include_once RACINE . '/service/EtudiantService.php';
            $es = new EtudiantService();
            foreach ($es->findAll() as $e) { ?>
                <tr>
                    <td><?php echo $e->getId(); ?></td>
                    <td><?php echo $e->getNom(); ?></td>
                    <td><?php echo $e->getPrenom(); ?></td>
                    <td><?php echo $e->getVille(); ?></td>
                    <td><?php echo $e->getSexe(); ?></td>
                    <td><img src="<?php echo $e->getPhoto(); ?>" alt="Photo de <?php echo $e->getNom(); ?>" width="50" /></td>
                    <td><a href="controller/deleteEtudiant.php?id=<?php echo $e->getId(); ?>">Supprimer</a></td>
                    <td><a href="controller/updateEtudiant.php?id=<?php echo $e->getId(); ?>">Modifier</a></td>
                </tr>
            <?php } ?>
        </tbody>
    </table>
</body>

</html>
