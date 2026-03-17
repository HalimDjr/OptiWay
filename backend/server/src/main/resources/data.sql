-- =============================================
-- RESET COMPLET
-- =============================================
TRUNCATE TABLE commande_entity CASCADE;
TRUNCATE TABLE produit_entity CASCADE;
TRUNCATE TABLE tournee_entity CASCADE;
TRUNCATE TABLE solution_entity CASCADE;
TRUNCATE TABLE entrepot_entity CASCADE;
TRUNCATE TABLE livreur_entity CASCADE;
TRUNCATE TABLE vehicule_entity CASCADE;
TRUNCATE TABLE equipe_entity CASCADE;
TRUNCATE TABLE adresse_entity CASCADE;

-- =============================================
-- ADRESSES (200 adresses region Grenoble)
-- =============================================
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (1, 84, 'Boulevard Gambetta', 'Varces-Allieres', 38760, 'France', 45.116738, 5.724491);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (2, 3, 'allee des acacias', 'Saint-Egreve', 38382, 'France', 45.22074536926136, 5.688257832674358);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (3, 252, 'Chemin des Bargeonniers', 'Vaulnaveys-le-Haut', 38529, 'France', 45.12556744925646, 5.8111572970032315);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (4, 13, 'Rue de la Capuche', 'Vizille', 38220, 'France', 45.070048, 5.770646);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (5, 32, 'Rue Thiers', 'Domene', 38420, 'France', 45.203087, 5.842538);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (6, 0, 'Chemin du Pre Figaroud', 'Saint-Nazaire-les-Eymes', 38431, 'France', 45.25902034318054, 5.853631757130329);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (7, 115, 'Allee Fantin Latour', 'Montbonnot-Saint-Martin', 38249, 'France', 45.235115354730304, 5.819836063071854);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (8, 13, 'Rue Abbe Gregoire', 'Grenoble', 38000, 'France', 45.193106, 5.734439);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (9, 45, 'Rue Racine', 'Pontcharra', 38530, 'France', 45.433553, 6.013889);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (10, 39, 'Boulevard Marechal Foch', 'Grenoble', 38185, 'France', 45.18055674777097, 5.720453458228962);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (11, 6, 'Rue Edouard Rey', 'Grenoble', 38000, 'France', 45.189736, 5.748971);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (12, 0, 'Impasse du Pre Peilloud', 'Saint-Martin-d Uriage', 38422, 'France', 45.142642046347376, 5.8392617068382);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (13, 7, 'Rue Louis Peclet', 'La Tronche', 38516, 'France', 45.20119904249928, 5.756629959766207);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (14, 1502, 'Route du General de Gaulle', 'Jarrie', 38200, 'France', 45.11852789818302, 5.762335123759017);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (15, 90, 'Place Grenette', 'Seyssinet-Pariset', 38170, 'France', 45.184786, 5.681853);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (16, 8, 'Boulevard de la Chantourne', 'La Tronche', 38516, 'France', 45.199821067463276, 5.756100624340419);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (17, 114, 'Chemin de Ribotiere', 'Saint-Ismier', 38397, 'France', 45.249071061693805, 5.825373880162429);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (18, 0, 'Place Salvador Allende', 'Fontaine', 38169, 'France', 45.19999091906352, 5.6943322196401365);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (19, 4551, 'route de montchaffrey', 'Vaulnaveys-le-Bas', 38528, 'France', 45.1017966574565, 5.825856581913525);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (20, 201, 'Route de Saint-nizier', 'Seyssins', 38486, 'France', 45.16039420383785, 5.671869205046425);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (21, 47, 'Impasse des Lilas', 'Varces-Allieres', 38760, 'France', 45.117933, 5.714507);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (22, 298, 'Chemin Andre Tisserand', 'Saint-Martin-le-Vinoux', 38423, 'France', 45.21937919948162, 5.72452349574968);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (23, 66, 'Rue Brocherie', 'Claix', 38640, 'France', 45.141214, 5.684729);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (24, 1143, 'Route du Rocharey', 'Saint-Martin-d Uriage', 38422, 'France', 45.178884563484445, 5.8693871285549335);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (25, 1, 'Route de Valence', 'Veurey-Voroize', 38540, 'France', 45.26977810419137, 5.619957123234859);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (26, 1, 'Chemin du 13 Juin 1944', 'Sassenage', 38474, 'France', 45.228777696272665, 5.6595266922061755);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (27, 1, 'Chemin de Batonniere', 'Corenc', 38126, 'France', 45.227408957057946, 5.754152952029467);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (28, 0, 'Pomarey', 'Proveysieux', 38325, 'France', 45.281151384667666, 5.713514380270205);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (29, 155, 'Chemin de l Olympe', 'Brie-et-Angonnes', 38059, 'France', 45.11663897517542, 5.779794089456186);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (30, 0, 'Chemin des Roux', 'Vaulnaveys-le-Haut', 38529, 'France', 45.12907368780499, 5.831592701344816);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (31, 37, 'Boulevard des Alpes', 'Meylan', 38229, 'France', 45.20338467020293, 5.771085225564686);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (32, 113, 'Impasse du Grand Pre', 'Vaulnaveys-le-Haut', 38529, 'France', 45.12921974544013, 5.830960971351838);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (33, 66, 'Rue Jean Moulin', 'Jarrie', 38200, 'France', 45.10447831494429, 5.750169160995164);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (34, 492, 'Route de Bens', 'Le Sappey-en-Chartreuse', 38471, 'France', 45.266019925865194, 5.7694248627549936);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (35, 1, 'Rue George Sand', 'Echirolles', 38151, 'France', 45.14516977090725, 5.734230606636127);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (36, 14, 'Allee de la Roseliere', 'Meylan', 38229, 'France', 45.21267543910739, 5.784765093555746);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (37, 70, 'Avenue du Vercors', 'Seyssinet-Pariset', 38485, 'France', 45.17609824608119, 5.68498038302522);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (38, 75, 'Avenue de l Eygala', 'Corenc', 38126, 'France', 45.21265081866631, 5.761879013454102);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (39, 6, 'Rue Pierre de Coubertin', 'Sassenage', 38474, 'France', 45.21393313641468, 5.668997470438934);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (40, 4, 'Rue Pierre de Coubertin', 'Sassenage', 38474, 'France', 45.212325481099754, 5.669801797159754);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (41, 38, 'Avenue de Verdun', 'Meylan', 38229, 'France', 45.20148317473588, 5.758914182925579);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (42, 1660, 'Rue de la Piscine', 'Gieres', 38179, 'France', 45.19742330798439, 5.7750029956643205);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (43, 451, 'Chemin de Tartaix', 'Montbonnot-Saint-Martin', 38249, 'France', 45.230396455423886, 5.813551791565689);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (44, 67, 'Rue Championnet', 'Echirolles', 38130, 'France', 45.161488, 5.710252);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (45, 106, 'Boulevard Gambetta', 'Echirolles', 38130, 'France', 45.158344, 5.73157);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (46, 343, 'Route des Molettes', 'Revel', 38334, 'France', 45.18524807699487, 5.887625753795034);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (47, 360, 'Rue Aristide Berges', 'Montbonnot-Saint-Martin', 38249, 'France', 45.22217078621841, 5.817172815555816);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (48, 2208, 'Route de Sarcenas', 'Quaix-en-Chartreuse', 38328, 'France', 45.2499363792691, 5.73833055499814);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (49, 17, 'Boulevard des Alpes', 'Saint-Martin-d Heres', 38400, 'France', 45.167573, 5.745314);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (50, 2, 'Rue Pablo Picasso', 'Echirolles', 38151, 'France', 45.14576298618681, 5.697580540992107);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (51, 189, 'Route de Gervais', 'Sarcenas', 38472, 'France', 45.276268188205414, 5.74874888797366);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (52, 5, 'Rue de l Ecole', 'Seyssinet-Pariset', 38485, 'France', 45.16938040714821, 5.665019329925951);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (53, 47, 'Allee des Semaises', 'Saint-Ismier', 38397, 'France', 45.23741473440652, 5.82368256189112);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (54, 135, 'Allee des Centaurees', 'Saint-Ismier', 38397, 'France', 45.25080526144046, 5.824803075119628);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (55, 24, 'Rue de la Resistance', 'Seyssinet-Pariset', 38485, 'France', 45.178605969967215, 5.684991395481724);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (56, 1441, 'Rue de la Piscine', 'Gieres', 38179, 'France', 45.19802228670107, 5.771884971705145);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (57, 80, 'Rue Paul Bert', 'Vizille', 38220, 'France', 45.079942, 5.764177);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (58, 11, 'Rue des Glairons', 'Saint-Martin-d Heres', 38421, 'France', 45.18665093944328, 5.760746165485002);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (59, 71, 'Rue des Arts', 'Seyssinet-Pariset', 38170, 'France', 45.166224, 5.683861);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (60, 232, 'Rue Marcel Paul', 'Jarrie', 38200, 'France', 45.10315236180867, 5.752955529364327);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (61, 8, 'Chemin du Tracollet', 'Veurey-Voroize', 38540, 'France', 45.2779544179408, 5.6093103582822215);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (62, 532, 'Allee des Chenes', 'Varces-Allieres-et-Risset', 38524, 'France', 45.10063632209055, 5.665707169110246);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (63, 63, 'Avenue Lucie Aubrac', 'Varces-Allieres', 38760, 'France', 45.123668, 5.724188);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (64, 4, 'Avenue du Vercors', 'Saint-Egreve', 38120, 'France', 45.221641, 5.6807);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (65, 46, 'Route du Peuil', 'Claix', 38111, 'France', 45.13729191927372, 5.6621656018733875);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (66, 6, 'Allee des Moscaries', 'Meylan', 38229, 'France', 45.21858606043225, 5.796701583352237);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (67, 5, 'Rue de l Oratoire', 'Bresson', 38057, 'France', 45.13877575954202, 5.740966509223249);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (68, 106, 'Rue Blanchard', 'Meylan', 38240, 'France', 45.222259, 5.78689);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (69, 62, 'Cours Jean Jaures', 'Crolles', 38920, 'France', 45.265921, 5.874478);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (70, 0, 'Rue Jean Coppier', 'Domene', 38150, 'France', 45.209831583854054, 5.84302819186721);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (71, 630, 'Chemin du Pre de l Achard', 'Saint-Nazaire-les-Eymes', 38431, 'France', 45.263185488566904, 5.846718992594746);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (72, 57, 'Chemin du Pit', 'Voreppe', 38565, 'France', 45.2669075219362, 5.653919690096442);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (73, 51, 'Chemin des Sources', 'Saint-Martin-d Uriage', 38422, 'France', 45.14494986154567, 5.858812496658993);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (74, 1118, 'Route de Rochetiere', 'Saint-Nizier-du-Moucherotte', 38433, 'France', 45.18386211157972, 5.636588009279233);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (75, 3, 'Allee des Grillons', 'Corenc', 38126, 'France', 45.21317552447746, 5.767038885870744);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (76, 169, 'Avenue des Martyrs', 'Grenoble', 38185, 'France', 45.21103478256804, 5.68616343979594);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (77, 0, 'chemin la bourreliere', 'Saint-Egreve', 38382, 'France', 45.23455118321118, 5.689136826776538);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (78, 518, 'Chemin des Perouses', 'Jarrie', 38200, 'France', 45.10708210602497, 5.74342614802259);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (79, 48, 'Rue des Ecrins', 'Grenoble', 38000, 'France', 45.170762, 5.735972);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (80, 28, 'Grand-Rue', 'Bresson', 38057, 'France', 45.13761229507616, 5.74839270907085);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (81, 280, 'Rue Felix Faure', 'Saint-Martin-le-Vinoux', 38423, 'France', 45.215437719354604, 5.693637671897045);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (82, 410, 'Chemin de Bois Claret', 'Bernin', 38039, 'France', 45.25943582287203, 5.86836156403246);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (83, 17, 'Rue de Marcelline', 'Le Pont-de-Claix', 38317, 'France', 45.125208957024626, 5.699698135247551);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (84, 4260, 'route de montchaffrey', 'Vaulnaveys-le-Bas', 38528, 'France', 45.10191530615122, 5.823781789983783);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (85, 75, 'Boulevard Joseph Vallier', 'Sassenage', 38360, 'France', 45.212882, 5.656713);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (86, 2348, 'Route du Balcon de Belledonne', 'Revel', 38334, 'France', 45.19415077658327, 5.8784297524028775);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (87, 292, 'Chemin des Noyers', 'Noyarey', 38281, 'France', 45.2480836872904, 5.6299162685168795);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (88, 65, 'Rue du Doyen Gosse', 'Vizille', 38220, 'France', 45.078841, 5.764694);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (89, 232, 'Route des Contamines', 'Revel', 38334, 'France', 45.18773940845043, 5.872940493304431);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (90, 5, 'Mail Marcel Cachin', 'Fontaine', 38169, 'France', 45.19449416794835, 5.692430609347117);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (91, 5, 'Allee des Tonnelles', 'Meylan', 38229, 'France', 45.21913728552743, 5.79746329334367);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (92, 580, 'Rue des Becasses', 'Crolles', 38140, 'France', 45.27356461127289, 5.887295603961857);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (93, 7, 'Rue du Sornin', 'Fontaine', 38169, 'France', 45.20608032027278, 5.687534974289792);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (94, 98, 'Avenue Viallet', 'Crolles', 38920, 'France', 45.269137, 5.87149);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (95, 2, 'rue de bellevue', 'Saint-Egreve', 38382, 'France', 45.24041833191286, 5.689760743344387);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (96, 96, 'Rue du Vercors', 'Grenoble', 38185, 'France', 45.19326814807197, 5.703188428711302);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (97, 64, 'Chemin de la Viscose', 'Saint-Egreve', 38120, 'France', 45.218907, 5.698977);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (98, 488, 'Chemin du Fangeat', 'Saint-Ismier', 38397, 'France', 45.24340955191145, 5.836219225327867);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (99, 1, 'Rue du Pic Saint Michel', 'Claix', 38111, 'France', 45.111799926061636, 5.684943786210776);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (100, 475, 'Route de Bon Repos', 'Jarrie', 38200, 'France', 45.11097411767559, 5.754493533341789);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (101, 15, 'Rue des Sources', 'Claix', 38111, 'France', 45.117031190443726, 5.695824371865036);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (102, 2, 'Avenue de Belledonne', 'Villard-Bonnot', 38547, 'France', 45.24519472654979, 5.898411029581486);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (103, 20, 'Chemin de Preydieres', 'Vaulnaveys-le-Bas', 38528, 'France', 45.10800472574496, 5.813759282509707);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (104, 53, 'Rue Compagnie Stephane', 'Le Versoud', 38538, 'France', 45.213474856688535, 5.8590108789253135);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (105, 34, 'Place Grenette', 'Claix', 38640, 'France', 45.140552, 5.680926);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (106, 6, 'Chemin Saint Ferjus', 'La Tronche', 38516, 'France', 45.2036591459813, 5.742409375042583);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (107, 45, 'Rue du Vercors', 'Saint-Martin-d Heres', 38400, 'France', 45.165151, 5.764799);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (108, 285, 'Rue Lavoisier', 'Montbonnot-Saint-Martin', 38249, 'France', 45.21807156193337, 5.812055267362327);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (109, 11, 'Avenue Pierre Semard', 'Varces-Allieres', 38760, 'France', 45.119507, 5.711689);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (110, 82, 'Rue Racine', 'Echirolles', 38130, 'France', 45.142048, 5.70834);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (111, 98, 'Boulevard Agutte Sembat', 'Claix', 38640, 'France', 45.146746, 5.686685);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (112, 11, 'Avenue des Maquis du Gresivaudan', 'La Tronche', 38516, 'France', 45.20431188733052, 5.7480181389890435);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (113, 17, 'rue de l industrie', 'Saint-Egreve', 38382, 'France', 45.22316466205933, 5.678277384468273);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (114, 485, 'Route de Chambery', 'Le Versoud', 38538, 'France', 45.22533233474214, 5.871043886238188);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (115, 4580, 'Route de la Charmette', 'Proveysieux', 38325, 'France', 45.27750573808063, 5.7080608028943765);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (116, 40, 'Route du Rocharey', 'Saint-Martin-d Uriage', 38422, 'France', 45.17341870767917, 5.870423600061386);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (117, 1175, 'Chemin de Pre Diot', 'Saint-Ismier', 38397, 'France', 45.23972947234248, 5.833673087009715);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (118, 165, 'Route de Saint-Nizier', 'Seyssinet-Pariset', 38485, 'France', 45.16840302141302, 5.672700508947024);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (119, 1, 'Chemin de Vence', 'Corenc', 38126, 'France', 45.23907323715886, 5.749592267256846);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (120, 145, 'Rue de Chartreuse', 'Le Versoud', 38538, 'France', 45.2127360584641, 5.851588898624594);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (121, 6000, 'Route de Chamrousse', 'Saint-Martin-d Uriage', 38422, 'France', 45.153181145803806, 5.871401193560595);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (122, 0, 'Echangeur du Synchrotron', 'Grenoble', 38185, 'France', 45.207030012027815, 5.686891165390374);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (123, 22, 'Chemin de l Ile d Amour', 'Meylan', 38229, 'France', 45.200363144491284, 5.779701951682023);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (124, 0, 'Le Petit Port', 'Veurey-Voroize', 38540, 'France', 45.288295397058576, 5.610833842629527);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (125, 366, 'Chemin de Pageonniere', 'Saint-Ismier', 38397, 'France', 45.247355154375455, 5.838098036536706);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (126, 83, 'Cours Berriat', 'Grenoble', 38185, 'France', 45.18834162263813, 5.713696436525633);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (127, 123, 'Chemin des Vignes', 'Brie-et-Angonnes', 38059, 'France', 45.14312920382946, 5.779470318298176);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (128, 2, 'Impasse du Rocher', 'Echirolles', 38151, 'France', 45.14857042400359, 5.6922129026432815);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (129, 2150, 'Route d Uriage', 'Saint-Martin-d Uriage', 38422, 'France', 45.13833158484577, 5.830291364358605);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (130, 10, 'Rue des 4 Seterees', 'Fontanil-Cornillon', 38170, 'France', 45.24954627398736, 5.656899173440651);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (131, 30, 'Rue Jean-Baptiste Clement', 'Echirolles', 38151, 'France', 45.144450194779786, 5.737534939279757);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (132, 1240, 'Route de la Chapelle', 'La Combe-de-Lancey', 38120, 'France', 45.22095834320612, 5.896708312648266);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (133, 1203, 'Route de Clemenciere', 'Quaix-en-Chartreuse', 38328, 'France', 45.24942092238125, 5.723968734438721);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (134, 72, 'Avenue Lucie Aubrac', 'Meylan', 38240, 'France', 45.212127, 5.783409);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (135, 103, 'Chemin de l Aragna', 'Jarrie', 38200, 'France', 45.11402098595359, 5.750586155535685);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (136, 1020, 'Chemin des Semaises', 'Saint-Ismier', 38397, 'France', 45.23104862879418, 5.8293415675333815);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (137, 10, 'Rue Albert Einstein', 'Eybens', 38158, 'France', 45.14365428470982, 5.746325939896764);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (138, 4, 'Chemin de la Croix Montfleury', 'Corenc', 38126, 'France', 45.21130932739435, 5.747674151069331);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (139, 125, 'Rue du Progres', 'Seyssinet-Pariset', 38485, 'France', 45.17499428685756, 5.700306273754004);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (140, 0, 'Route du Bouloud', 'Saint-Martin-d Uriage', 38422, 'France', 45.14170899606023, 5.8367157022303955);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (141, 2, 'Rue Guynemer', 'Villard-Bonnot', 38547, 'France', 45.23447737618351, 5.876523064708865);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (142, 0, 'Rue Albert Einstein', 'Eybens', 38158, 'France', 45.142785779837745, 5.745423164709972);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (143, 308, 'Chemin du Murgier', 'Vaulnaveys-le-Haut', 38529, 'France', 45.11546020033439, 5.825278329698848);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (144, 64, 'Route du Peuil', 'Claix', 38111, 'France', 45.12693610960463, 5.650510530485168);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (145, 125, 'Avenue Gabriel Peri', 'Saint-Martin-d Heres', 38421, 'France', 45.18497669374754, 5.765700799730589);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (146, 9, 'Avenue de Verdun', 'Meylan', 38240, 'France', 45.215404, 5.773803);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (147, 0, 'Rue de la Tannerie', 'Fontanil-Cornillon', 38170, 'France', 45.25287244601475, 5.653628705946498);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (148, 5320, 'Route d Ezy', 'Noyarey', 38281, 'France', 45.253838315830535, 5.603719819018406);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (149, 68, 'Rue de Montdragon', 'Voreppe', 38565, 'France', 45.25762953745254, 5.655293440578289);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (150, 608, 'Chemin de Combeloup', 'Murianette', 38271, 'France', 45.184122208744014, 5.828994127299912);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (151, 42, 'Rue de Bonne', 'Vizille', 38220, 'France', 45.072167, 5.778945);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (152, 164, 'Chemin du Ruisseau', 'Saint-Ismier', 38397, 'France', 45.237951119782956, 5.822744578678263);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (153, 9, 'Chemin du Vieux Chene', 'Meylan', 38229, 'France', 45.20635698401244, 5.783842302031089);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (154, 64, 'Avenue Lucie Aubrac', 'Saint-Martin-d Heres', 38400, 'France', 45.163296, 5.765473);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (155, 25, 'Rue de la Paix', 'Le Pont-de-Claix', 38800, 'France', 45.134342, 5.702806);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (156, 0, 'Chemin de la Marche', 'La Combe-de-Lancey', 38120, 'France', 45.22047111830305, 5.892801783256274);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (157, 70, 'Chemin de la Viscose', 'Vizille', 38220, 'France', 45.072762, 5.765201);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (158, 44, 'Allee Hector Berlioz', 'Saint-Martin-d Heres', 38421, 'France', 45.18765446132049, 5.75852086739786);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (159, 47, 'rue de champaviotte', 'Saint-Egreve', 38382, 'France', 45.22875469000755, 5.675189565266583);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (160, 68, 'Avenue des Martyrs', 'Crolles', 38920, 'France', 45.266463, 5.875149);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (161, 110, 'Allee de la Casamaures', 'Saint-Martin-le-Vinoux', 38423, 'France', 45.20104269802622, 5.7158976379015725);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (162, 825, 'Chemin de Chamoussiere', 'Voreppe', 38565, 'France', 45.259595291607354, 5.655987746336325);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (163, 30, 'Chemin des Pataches', 'Sassenage', 38474, 'France', 45.21459239640859, 5.644726260699209);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (164, 44, 'Rue des Ciments', 'Seyssins', 38486, 'France', 45.151078834578385, 5.687044868725155);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (165, 45, 'Avenue des 7 Laux', 'Meylan', 38229, 'France', 45.21553635695254, 5.7816703726561265);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (166, 47, 'Allee des Roses', 'Echirolles', 38130, 'France', 45.164342, 5.725646);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (167, 130, 'Route de la Poya', 'Saint-Jean-le-Vieux', 38404, 'France', 45.211501205876985, 5.883470004743494);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (168, 83, 'Chemin de la Caillate', 'Bernin', 38039, 'France', 45.267255993230236, 5.86110309956277);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (169, 368, 'Chemin des Bauches', 'Noyarey', 38281, 'France', 45.240606055576, 5.639781867419814);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (170, 304, 'Chemin du Bas de Montquaix', 'Quaix-en-Chartreuse', 38328, 'France', 45.252385462208935, 5.740903052882964);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (171, 0, 'Route de la Combe de Champagnier', 'Echirolles', 38151, 'France', 45.133159195901655, 5.7171469846058915);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (172, 690, 'Route de Planfay', 'Proveysieux', 38325, 'France', 45.27793829294752, 5.711580245084482);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (173, 201, 'Route de Saint-nizier', 'Seyssins', 38486, 'France', 45.16054950690448, 5.671923897952691);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (174, 50, 'Rue Ampere', 'Saint-Martin-d Heres', 38400, 'France', 45.160657, 5.74964);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (175, 12, 'Chemin du Neron', 'Sassenage', 38474, 'France', 45.20824052164235, 5.675155243353289);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (176, 271, 'Chemin de Rabit', 'Herbeys', 38188, 'France', 45.135578194254336, 5.798863098737961);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (177, 0, 'Chemin de la Fontanette', 'Claix', 38111, 'France', 45.12370395876424, 5.667252907030916);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (178, 85, 'Chemin Combe et Verney', 'Brie-et-Angonnes', 38059, 'France', 45.141366309276236, 5.773494177119276);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (179, 477, 'Route de la Chapelle', 'La Combe-de-Lancey', 38120, 'France', 45.22807408597455, 5.8987231997634035);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (180, 37, 'Boulevard Marechal Foch', 'Grenoble', 38185, 'France', 45.18033537701525, 5.721394643128216);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (181, 70, 'Allee de la Fraternite', 'Domene', 38150, 'France', 45.20773692499107, 5.8302481924138);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (182, 11, 'Rue Charles Piot', 'Eybens', 38158, 'France', 45.157713956144725, 5.751977158265993);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (183, 7, 'Rue du Tour De L Eau', 'Saint-Martin-d Heres', 38421, 'France', 45.186929588272854, 5.7716776924496465);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (184, 17, 'Rue de l Isle', 'Saint-Martin-d Heres', 38421, 'France', 45.184787613665456, 5.770359509573421);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (185, 11, 'Rue des Aiguinards', 'Meylan', 38229, 'France', 45.20986934277765, 5.763168808145159);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (186, 34, 'Avenue Berriat', 'Saint-Egreve', 38120, 'France', 45.242856, 5.683047);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (187, 168, 'Avenue des Martyrs', 'Grenoble', 38185, 'France', 45.21003559576717, 5.688136744677989);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (188, 47, 'rue de champaviotte', 'Saint-Egreve', 38382, 'France', 45.22875469000755, 5.675189565266583);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (189, 1, 'Rue Porte des Pallaches', 'Voreppe', 38565, 'France', 45.29771485257483, 5.639173807322679);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (190, 55, 'Rue des Tisserands', 'Vizille', 38220, 'France', 45.089816, 5.765485);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (191, 825, 'Chemin de Chamoussiere', 'Voreppe', 38565, 'France', 45.259595291607354, 5.655987746336325);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (192, 30, 'Chemin des Pataches', 'Sassenage', 38474, 'France', 45.21459239640859, 5.644726260699209);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (193, 44, 'Rue des Ciments', 'Seyssins', 38486, 'France', 45.151078834578385, 5.687044868725155);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (194, 45, 'Avenue des 7 Laux', 'Meylan', 38229, 'France', 45.21553635695254, 5.7816703726561265);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (195, 37, 'Allee des Treilles', 'Voreppe', 38565, 'France', 45.2868628532717, 5.637460320830422);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (196, 130, 'Route de la Poya', 'Saint-Jean-le-Vieux', 38404, 'France', 45.211501205876985, 5.883470004743494);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (197, 32, 'Rue de la Republique', 'Echirolles', 38130, 'France', 45.168871, 5.710645);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (198, 368, 'Chemin des Bauches', 'Noyarey', 38281, 'France', 45.240606055576, 5.639781867419814);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (199, 304, 'Chemin du Bas de Montquaix', 'Quaix-en-Chartreuse', 38328, 'France', 45.252385462208935, 5.740903052882964);
INSERT INTO adresse_entity (id, numero_rue, rue, ville, code_postal, pays, latitude, longitude)
VALUES (200, 0, 'Route de la Combe de Champagnier', 'Echirolles', 38151, 'France', 45.133159195901655, 5.7171469846058915);

-- =============================================
-- EQUIPES (8 equipes)
-- =============================================
INSERT INTO equipe_entity (numero_equipe, nb_heures_max) VALUES (1, 8.5);
INSERT INTO equipe_entity (numero_equipe, nb_heures_max) VALUES (2, 7.0);
INSERT INTO equipe_entity (numero_equipe, nb_heures_max) VALUES (3, 9.0);
INSERT INTO equipe_entity (numero_equipe, nb_heures_max) VALUES (4, 8.0);
INSERT INTO equipe_entity (numero_equipe, nb_heures_max) VALUES (5, 7.5);
INSERT INTO equipe_entity (numero_equipe, nb_heures_max) VALUES (6, 9.5);
INSERT INTO equipe_entity (numero_equipe, nb_heures_max) VALUES (7, 8.0);
INSERT INTO equipe_entity (numero_equipe, nb_heures_max) VALUES (8, 7.0);

-- =============================================
-- VEHICULES (8 vehicules)
-- =============================================
INSERT INTO vehicule_entity (matricule, capacite_poids_max, capacite_volume_max)
VALUES (101, 1500.0, 12.0);
INSERT INTO vehicule_entity (matricule, capacite_poids_max, capacite_volume_max)
VALUES (102, 2000.0, 18.0);
INSERT INTO vehicule_entity (matricule, capacite_poids_max, capacite_volume_max)
VALUES (103, 1200.0, 10.0);
INSERT INTO vehicule_entity (matricule, capacite_poids_max, capacite_volume_max)
VALUES (104, 1800.0, 15.0);
INSERT INTO vehicule_entity (matricule, capacite_poids_max, capacite_volume_max)
VALUES (105, 1600.0, 13.0);
INSERT INTO vehicule_entity (matricule, capacite_poids_max, capacite_volume_max)
VALUES (106, 2200.0, 20.0);
INSERT INTO vehicule_entity (matricule, capacite_poids_max, capacite_volume_max)
VALUES (107, 1400.0, 11.0);
INSERT INTO vehicule_entity (matricule, capacite_poids_max, capacite_volume_max)
VALUES (108, 1700.0, 14.0);

INSERT INTO livreur_entity (id_livreur, nom, prenom, permis, telephone, equipe2_numero_equipe) VALUES (1, 'Dupont', 'Jean', true, '0600000001', NULL);
INSERT INTO livreur_entity (id_livreur, nom, prenom, permis, telephone, equipe2_numero_equipe) VALUES (2, 'Martin', 'Marie', false, '0600000002', 1);
INSERT INTO livreur_entity (id_livreur, nom, prenom, permis, telephone, equipe2_numero_equipe) VALUES (3, 'Bernard', 'Pierre', true, '0600000003', NULL);
INSERT INTO livreur_entity (id_livreur, nom, prenom, permis, telephone, equipe2_numero_equipe) VALUES (4, 'Petit', 'Emma', false, '0600000004', 2);
INSERT INTO livreur_entity (id_livreur, nom, prenom, permis, telephone, equipe2_numero_equipe) VALUES (5, 'Moreau', 'Lucas', true, '0600000005', NULL);
INSERT INTO livreur_entity (id_livreur, nom, prenom, permis, telephone, equipe2_numero_equipe) VALUES (6, 'Simon', 'Lea', false, '0600000006', 3);
INSERT INTO livreur_entity (id_livreur, nom, prenom, permis, telephone, equipe2_numero_equipe) VALUES (7, 'Laurent', 'Noah', true, '0600000007', NULL);
INSERT INTO livreur_entity (id_livreur, nom, prenom, permis, telephone, equipe2_numero_equipe) VALUES (8, 'Dubois', 'Sophie', false, '0600000008', 4);
INSERT INTO livreur_entity (id_livreur, nom, prenom, permis, telephone, equipe2_numero_equipe) VALUES (9, 'Leroy', 'Thomas', true, '0600000009', NULL);
INSERT INTO livreur_entity (id_livreur, nom, prenom, permis, telephone, equipe2_numero_equipe) VALUES (10, 'Garcia', 'Clara', false, '0600000010', 5);
INSERT INTO livreur_entity (id_livreur, nom, prenom, permis, telephone, equipe2_numero_equipe) VALUES (11, 'Roux', 'Hugo', true, '0600000011', NULL);
INSERT INTO livreur_entity (id_livreur, nom, prenom, permis, telephone, equipe2_numero_equipe) VALUES (12, 'David', 'Julie', false, '0600000012', 6);
INSERT INTO livreur_entity (id_livreur, nom, prenom, permis, telephone, equipe2_numero_equipe) VALUES (13, 'Bertrand', 'Luca', true, '0600000013', NULL);
INSERT INTO livreur_entity (id_livreur, nom, prenom, permis, telephone, equipe2_numero_equipe) VALUES (14, 'Morel', 'Alice', false, '0600000014', 7);
INSERT INTO livreur_entity (id_livreur, nom, prenom, permis, telephone, equipe2_numero_equipe) VALUES (15, 'Fournier', 'Nathan', true, '0600000015', NULL);
INSERT INTO livreur_entity (id_livreur, nom, prenom, permis, telephone, equipe2_numero_equipe) VALUES (16, 'Girard', 'Laura', false, '0600000016', 8);
-- =============================================
-- CONDUCTEURS ET VEHICULES PAR EQUIPE
-- =============================================
UPDATE equipe_entity SET conducteur_id_livreur = 1,  vehicule_matricule = 101 WHERE numero_equipe = 1;
UPDATE equipe_entity SET conducteur_id_livreur = 3,  vehicule_matricule = 102 WHERE numero_equipe = 2;
UPDATE equipe_entity SET conducteur_id_livreur = 5,  vehicule_matricule = 103 WHERE numero_equipe = 3;
UPDATE equipe_entity SET conducteur_id_livreur = 7,  vehicule_matricule = 104 WHERE numero_equipe = 4;
UPDATE equipe_entity SET conducteur_id_livreur = 9,  vehicule_matricule = 105 WHERE numero_equipe = 5;
UPDATE equipe_entity SET conducteur_id_livreur = 11, vehicule_matricule = 106 WHERE numero_equipe = 6;
UPDATE equipe_entity SET conducteur_id_livreur = 13, vehicule_matricule = 107 WHERE numero_equipe = 7;
UPDATE equipe_entity SET conducteur_id_livreur = 15, vehicule_matricule = 108 WHERE numero_equipe = 8;
-- =============================================
-- ENTREPOT (apres les adresses)
-- =============================================
INSERT INTO entrepot_entity (id, nom, adresse_id)
VALUES (1, 'Entrepot Central Grenoble', 10);

-- =============================================
-- PRODUITS (20 produits)
-- =============================================
INSERT INTO produit_entity (reference, nom, commande_id) VALUES ('PROD-001', 'Television 55 pouces', NULL);
INSERT INTO produit_entity (reference, nom, commande_id) VALUES ('PROD-002', 'Refrigerateur', NULL);
INSERT INTO produit_entity (reference, nom, commande_id) VALUES ('PROD-003', 'Lave-linge', NULL);
INSERT INTO produit_entity (reference, nom, commande_id) VALUES ('PROD-004', 'Micro-ondes', NULL);
INSERT INTO produit_entity (reference, nom, commande_id) VALUES ('PROD-005', 'Canape 3 places', NULL);
INSERT INTO produit_entity (reference, nom, commande_id) VALUES ('PROD-006', 'Table basse', NULL);
INSERT INTO produit_entity (reference, nom, commande_id) VALUES ('PROD-007', 'Armoire', NULL);
INSERT INTO produit_entity (reference, nom, commande_id) VALUES ('PROD-008', 'Lit double', NULL);
INSERT INTO produit_entity (reference, nom, commande_id) VALUES ('PROD-009', 'Bureau', NULL);
INSERT INTO produit_entity (reference, nom, commande_id) VALUES ('PROD-010', 'Chaise de bureau', NULL);
INSERT INTO produit_entity (reference, nom, commande_id) VALUES ('PROD-011', 'Aspirateur', NULL);
INSERT INTO produit_entity (reference, nom, commande_id) VALUES ('PROD-012', 'Machine a cafe', NULL);
INSERT INTO produit_entity (reference, nom, commande_id) VALUES ('PROD-013', 'Friteuse', NULL);
INSERT INTO produit_entity (reference, nom, commande_id) VALUES ('PROD-014', 'Robot cuiseur', NULL);
INSERT INTO produit_entity (reference, nom, commande_id) VALUES ('PROD-015', 'Ecran PC', NULL);
INSERT INTO produit_entity (reference, nom, commande_id) VALUES ('PROD-016', 'Imprimante', NULL);
INSERT INTO produit_entity (reference, nom, commande_id) VALUES ('PROD-017', 'Climatiseur', NULL);
INSERT INTO produit_entity (reference, nom, commande_id) VALUES ('PROD-018', 'Radiateur', NULL);
INSERT INTO produit_entity (reference, nom, commande_id) VALUES ('PROD-019', 'Lave-vaisselle', NULL);
INSERT INTO produit_entity (reference, nom, commande_id) VALUES ('PROD-020', 'Seche-linge', NULL);

-- =============================================
-- COMMANDES (200 commandes)
-- =============================================
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-001', '2026-03-16', 'LIVREE', 58.8, 1.2, 1);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-002', '2026-03-15', 'NON_PLANIFIEE', 26.0, 1.4, 2);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-003', '2026-04-30', 'NON_PLANIFIEE', 16.0, 2.0, 3);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-004', '2026-04-17', 'LIVREE', 14.6, 1.7, 4);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-005', '2026-04-21', 'EN_COURS', 72.1, 0.7, 5);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-006', '2026-04-23', 'NON_PLANIFIEE', 31.7, 1.5, 6);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-007', '2026-03-20', 'NON_PLANIFIEE', 22.4, 0.3, 7);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-008', '2026-03-30', 'LIVREE', 23.4, 1.9, 8);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-009', '2026-03-17', 'LIVREE', 78.8, 1.1, 9);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-010', '2026-04-22', 'NON_PLANIFIEE', 67.0, 1.7, 10);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-011', '2026-04-26', 'PLANIFIEE', 78.8, 2.0, 11);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-012', '2026-03-24', 'NON_PLANIFIEE', 11.3, 0.7, 12);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-013', '2026-03-30', 'NON_PLANIFIEE', 55.5, 1.9, 13);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-014', '2026-03-19', 'NON_PLANIFIEE', 25.3, 0.6, 14);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-015', '2026-03-29', 'LIVREE', 40.8, 1.3, 15);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-016', '2026-03-19', 'NON_PLANIFIEE', 30.3, 1.8, 16);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-017', '2026-03-19', 'NON_PLANIFIEE', 74.8, 0.8, 17);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-018', '2026-04-18', 'NON_PLANIFIEE', 48.6, 0.2, 18);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-019', '2026-03-21', 'NON_PLANIFIEE', 75.4, 1.0, 19);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-020', '2026-03-28', 'NON_PLANIFIEE', 48.2, 1.9, 20);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-021', '2026-03-30', 'LIVREE', 22.1, 0.5, 21);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-022', '2026-04-19', 'NON_PLANIFIEE', 54.9, 0.3, 22);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-023', '2026-03-23', 'PLANIFIEE', 55.5, 1.6, 23);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-024', '2026-03-25', 'NON_PLANIFIEE', 33.4, 0.1, 24);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-025', '2026-04-18', 'NON_PLANIFIEE', 68.8, 1.3, 25);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-026', '2026-04-20', 'NON_PLANIFIEE', 36.4, 0.1, 26);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-027', '2026-03-20', 'NON_PLANIFIEE', 60.7, 0.6, 27);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-028', '2026-04-26', 'NON_PLANIFIEE', 59.9, 1.2, 28);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-029', '2026-03-19', 'NON_PLANIFIEE', 74.7, 0.4, 29);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-030', '2026-04-24', 'NON_PLANIFIEE', 77.5, 1.6, 30);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-031', '2026-04-19', 'NON_PLANIFIEE', 21.4, 0.9, 31);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-032', '2026-03-19', 'NON_PLANIFIEE', 41.8, 0.5, 32);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-033', '2026-04-17', 'NON_PLANIFIEE', 22.2, 1.4, 33);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-034', '2026-04-17', 'NON_PLANIFIEE', 22.8, 1.9, 34);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-035', '2026-04-22', 'NON_PLANIFIEE', 5.6, 0.7, 35);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-036', '2026-04-20', 'NON_PLANIFIEE', 22.5, 0.5, 36);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-037', '2026-03-23', 'NON_PLANIFIEE', 11.7, 1.3, 37);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-038', '2026-03-26', 'NON_PLANIFIEE', 10.4, 1.1, 38);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-039', '2026-03-27', 'NON_PLANIFIEE', 15.4, 1.3, 39);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-040', '2026-03-21', 'NON_PLANIFIEE', 58.2, 1.3, 40);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-041', '2026-04-29', 'NON_PLANIFIEE', 8.5, 1.4, 41);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-042', '2026-03-16', 'NON_PLANIFIEE', 5.7, 0.3, 42);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-043', '2026-04-21', 'NON_PLANIFIEE', 40.2, 0.7, 43);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-044', '2026-04-16', 'EN_COURS', 15.1, 1.0, 44);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-045', '2026-04-27', 'EN_COURS', 25.9, 1.3, 45);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-046', '2026-03-20', 'NON_PLANIFIEE', 61.1, 1.8, 46);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-047', '2026-04-15', 'NON_PLANIFIEE', 75.8, 0.7, 47);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-048', '2026-04-19', 'NON_PLANIFIEE', 55.0, 0.2, 48);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-049', '2026-04-16', 'EN_COURS', 77.5, 1.5, 49);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-050', '2026-03-15', 'NON_PLANIFIEE', 63.2, 1.6, 50);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-051', '2026-03-29', 'NON_PLANIFIEE', 25.5, 1.0, 51);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-052', '2026-03-28', 'NON_PLANIFIEE', 8.6, 0.9, 52);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-053', '2026-04-18', 'NON_PLANIFIEE', 40.3, 1.0, 53);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-054', '2026-04-16', 'NON_PLANIFIEE', 52.8, 1.7, 54);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-055', '2026-03-25', 'NON_PLANIFIEE', 54.2, 1.4, 55);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-056', '2026-03-27', 'NON_PLANIFIEE', 36.7, 0.4, 56);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-057', '2026-03-21', 'PLANIFIEE', 12.6, 0.4, 57);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-058', '2026-04-19', 'NON_PLANIFIEE', 29.3, 0.8, 58);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-059', '2026-03-20', 'LIVREE', 65.3, 1.4, 59);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-060', '2026-04-29', 'NON_PLANIFIEE', 61.5, 1.6, 60);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-061', '2026-03-19', 'NON_PLANIFIEE', 36.0, 0.2, 61);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-062', '2026-03-22', 'NON_PLANIFIEE', 32.4, 1.3, 62);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-063', '2026-04-15', 'LIVREE', 12.3, 1.4, 63);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-064', '2026-03-24', 'PLANIFIEE', 27.3, 0.3, 64);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-065', '2026-03-18', 'NON_PLANIFIEE', 54.7, 0.3, 65);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-066', '2026-04-24', 'NON_PLANIFIEE', 35.9, 0.9, 66);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-067', '2026-04-19', 'NON_PLANIFIEE', 25.4, 0.1, 67);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-068', '2026-03-25', 'LIVREE', 20.1, 1.8, 68);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-069', '2026-04-29', 'LIVREE', 62.7, 1.4, 69);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-070', '2026-03-20', 'NON_PLANIFIEE', 31.0, 1.6, 70);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-071', '2026-03-29', 'NON_PLANIFIEE', 60.3, 1.1, 71);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-072', '2026-04-18', 'NON_PLANIFIEE', 73.3, 0.8, 72);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-073', '2026-04-27', 'NON_PLANIFIEE', 78.4, 0.9, 73);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-074', '2026-04-24', 'NON_PLANIFIEE', 19.2, 1.5, 74);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-075', '2026-04-26', 'NON_PLANIFIEE', 60.1, 2.0, 75);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-076', '2026-03-24', 'NON_PLANIFIEE', 49.8, 0.3, 76);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-077', '2026-04-19', 'NON_PLANIFIEE', 12.8, 0.6, 77);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-078', '2026-03-22', 'NON_PLANIFIEE', 27.7, 1.3, 78);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-079', '2026-03-16', 'LIVREE', 26.9, 0.8, 79);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-080', '2026-04-27', 'NON_PLANIFIEE', 27.0, 0.7, 80);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-081', '2026-04-18', 'NON_PLANIFIEE', 79.0, 0.6, 81);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-082', '2026-04-23', 'NON_PLANIFIEE', 21.3, 1.4, 82);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-083', '2026-04-27', 'NON_PLANIFIEE', 45.3, 1.3, 83);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-084', '2026-03-26', 'NON_PLANIFIEE', 62.9, 0.1, 84);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-085', '2026-03-22', 'PLANIFIEE', 73.6, 1.3, 85);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-086', '2026-04-19', 'NON_PLANIFIEE', 54.3, 0.2, 86);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-087', '2026-04-25', 'NON_PLANIFIEE', 53.8, 0.5, 87);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-088', '2026-03-26', 'EN_COURS', 11.5, 1.9, 88);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-089', '2026-04-28', 'NON_PLANIFIEE', 36.7, 0.3, 89);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-090', '2026-03-22', 'NON_PLANIFIEE', 57.4, 1.8, 90);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-091', '2026-04-22', 'NON_PLANIFIEE', 54.5, 1.7, 91);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-092', '2026-03-29', 'NON_PLANIFIEE', 23.1, 1.1, 92);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-093', '2026-03-21', 'NON_PLANIFIEE', 8.2, 1.0, 93);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-094', '2026-04-22', 'PLANIFIEE', 24.0, 1.4, 94);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-095', '2026-04-28', 'NON_PLANIFIEE', 57.4, 1.2, 95);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-096', '2026-04-29', 'NON_PLANIFIEE', 41.6, 1.9, 96);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-097', '2026-04-19', 'EN_COURS', 75.6, 0.5, 97);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-098', '2026-03-24', 'NON_PLANIFIEE', 28.3, 2.0, 98);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-099', '2026-04-15', 'NON_PLANIFIEE', 75.8, 1.0, 99);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-100', '2026-03-22', 'NON_PLANIFIEE', 68.7, 1.6, 100);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-101', '2026-04-24', 'NON_PLANIFIEE', 59.7, 1.7, 101);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-102', '2026-04-20', 'NON_PLANIFIEE', 51.7, 0.1, 102);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-103', '2026-04-26', 'NON_PLANIFIEE', 72.5, 0.2, 103);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-104', '2026-03-27', 'NON_PLANIFIEE', 51.2, 1.1, 104);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-105', '2026-04-30', 'LIVREE', 41.4, 0.1, 105);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-106', '2026-04-18', 'NON_PLANIFIEE', 7.7, 0.7, 106);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-107', '2026-03-28', 'LIVREE', 48.6, 1.7, 107);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-108', '2026-03-30', 'NON_PLANIFIEE', 9.0, 1.0, 108);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-109', '2026-04-16', 'LIVREE', 12.1, 1.0, 109);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-110', '2026-03-23', 'EN_COURS', 24.6, 0.5, 110);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-111', '2026-04-16', 'EN_COURS', 55.0, 0.5, 111);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-112', '2026-03-30', 'NON_PLANIFIEE', 5.0, 0.2, 112);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-113', '2026-04-20', 'NON_PLANIFIEE', 37.0, 0.6, 113);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-114', '2026-04-25', 'NON_PLANIFIEE', 15.9, 0.2, 114);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-115', '2026-03-21', 'NON_PLANIFIEE', 11.9, 1.2, 115);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-116', '2026-04-24', 'NON_PLANIFIEE', 65.2, 0.1, 116);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-117', '2026-03-28', 'NON_PLANIFIEE', 54.1, 1.9, 117);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-118', '2026-04-16', 'NON_PLANIFIEE', 11.7, 1.0, 118);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-119', '2026-04-30', 'NON_PLANIFIEE', 62.8, 0.5, 119);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-120', '2026-03-26', 'NON_PLANIFIEE', 10.9, 1.4, 120);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-121', '2026-04-15', 'NON_PLANIFIEE', 59.9, 1.6, 121);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-122', '2026-03-19', 'NON_PLANIFIEE', 58.9, 1.4, 122);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-123', '2026-04-24', 'NON_PLANIFIEE', 24.1, 0.6, 123);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-124', '2026-04-28', 'NON_PLANIFIEE', 55.6, 1.8, 124);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-125', '2026-03-26', 'NON_PLANIFIEE', 40.0, 1.1, 125);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-126', '2026-04-24', 'NON_PLANIFIEE', 61.1, 0.4, 126);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-127', '2026-04-23', 'NON_PLANIFIEE', 49.8, 0.6, 127);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-128', '2026-03-15', 'NON_PLANIFIEE', 59.9, 1.3, 128);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-129', '2026-04-27', 'NON_PLANIFIEE', 60.4, 0.1, 129);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-130', '2026-03-16', 'NON_PLANIFIEE', 6.3, 0.7, 130);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-131', '2026-04-19', 'NON_PLANIFIEE', 69.0, 1.1, 131);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-132', '2026-04-20', 'NON_PLANIFIEE', 38.7, 1.2, 132);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-133', '2026-04-19', 'NON_PLANIFIEE', 31.5, 0.2, 133);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-134', '2026-03-21', 'LIVREE', 68.1, 0.1, 134);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-135', '2026-04-28', 'NON_PLANIFIEE', 77.3, 1.9, 135);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-136', '2026-03-16', 'NON_PLANIFIEE', 28.1, 1.3, 136);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-137', '2026-04-23', 'NON_PLANIFIEE', 38.3, 1.1, 137);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-138', '2026-03-23', 'NON_PLANIFIEE', 8.7, 0.6, 138);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-139', '2026-03-28', 'NON_PLANIFIEE', 20.6, 0.1, 139);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-140', '2026-03-26', 'NON_PLANIFIEE', 10.0, 0.8, 140);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-141', '2026-03-29', 'NON_PLANIFIEE', 25.2, 0.5, 141);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-142', '2026-04-25', 'NON_PLANIFIEE', 41.3, 1.6, 142);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-143', '2026-03-26', 'NON_PLANIFIEE', 35.5, 1.4, 143);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-144', '2026-03-20', 'NON_PLANIFIEE', 36.8, 1.2, 144);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-145', '2026-04-23', 'NON_PLANIFIEE', 45.4, 0.9, 145);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-146', '2026-04-19', 'EN_COURS', 27.2, 0.2, 146);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-147', '2026-03-19', 'NON_PLANIFIEE', 58.0, 0.6, 147);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-148', '2026-04-20', 'NON_PLANIFIEE', 18.8, 1.4, 148);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-149', '2026-03-21', 'NON_PLANIFIEE', 51.7, 0.4, 149);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-150', '2026-03-29', 'NON_PLANIFIEE', 52.0, 0.7, 150);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-151', '2026-04-20', 'LIVREE', 30.0, 1.7, 151);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-152', '2026-04-30', 'NON_PLANIFIEE', 39.5, 0.7, 152);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-153', '2026-03-21', 'NON_PLANIFIEE', 67.5, 1.3, 153);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-154', '2026-03-28', 'PLANIFIEE', 43.7, 1.8, 154);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-155', '2026-03-19', 'PLANIFIEE', 75.4, 1.6, 155);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-156', '2026-03-28', 'NON_PLANIFIEE', 51.4, 1.7, 156);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-157', '2026-04-30', 'LIVREE', 19.3, 0.6, 157);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-158', '2026-04-23', 'NON_PLANIFIEE', 41.0, 1.1, 158);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-159', '2026-03-28', 'NON_PLANIFIEE', 53.0, 1.8, 159);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-160', '2026-04-23', 'PLANIFIEE', 24.4, 0.4, 160);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-161', '2026-04-29', 'NON_PLANIFIEE', 24.1, 1.3, 161);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-162', '2026-03-22', 'NON_PLANIFIEE', 56.7, 1.4, 162);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-163', '2026-03-22', 'NON_PLANIFIEE', 19.8, 1.8, 163);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-164', '2026-04-24', 'NON_PLANIFIEE', 38.1, 0.9, 164);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-165', '2026-03-26', 'NON_PLANIFIEE', 30.5, 0.2, 165);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-166', '2026-04-23', 'EN_COURS', 7.1, 0.9, 166);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-167', '2026-04-16', 'NON_PLANIFIEE', 75.2, 1.7, 167);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-168', '2026-03-27', 'NON_PLANIFIEE', 75.0, 2.0, 168);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-169', '2026-04-27', 'NON_PLANIFIEE', 46.0, 1.1, 169);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-170', '2026-03-22', 'NON_PLANIFIEE', 61.9, 0.9, 170);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-171', '2026-04-28', 'NON_PLANIFIEE', 13.1, 0.8, 171);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-172', '2026-04-19', 'NON_PLANIFIEE', 46.8, 1.0, 172);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-173', '2026-03-29', 'NON_PLANIFIEE', 45.3, 1.0, 173);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-174', '2026-04-19', 'PLANIFIEE', 37.2, 0.2, 174);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-175', '2026-04-26', 'NON_PLANIFIEE', 46.3, 1.4, 175);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-176', '2026-03-29', 'NON_PLANIFIEE', 32.2, 0.7, 176);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-177', '2026-04-26', 'NON_PLANIFIEE', 28.4, 0.3, 177);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-178', '2026-04-15', 'NON_PLANIFIEE', 79.7, 2.0, 178);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-179', '2026-03-15', 'NON_PLANIFIEE', 16.3, 0.8, 179);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-180', '2026-04-20', 'NON_PLANIFIEE', 22.2, 1.8, 180);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-181', '2026-04-30', 'NON_PLANIFIEE', 40.0, 0.9, 181);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-182', '2026-04-17', 'NON_PLANIFIEE', 78.9, 1.1, 182);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-183', '2026-03-20', 'NON_PLANIFIEE', 63.6, 1.5, 183);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-184', '2026-03-24', 'NON_PLANIFIEE', 46.9, 0.3, 184);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-185', '2026-03-19', 'NON_PLANIFIEE', 51.3, 1.0, 185);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-186', '2026-04-28', 'LIVREE', 78.7, 0.5, 186);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-187', '2026-04-25', 'NON_PLANIFIEE', 53.3, 1.7, 187);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-188', '2026-03-20', 'NON_PLANIFIEE', 48.8, 0.6, 188);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-189', '2026-04-17', 'NON_PLANIFIEE', 54.1, 1.2, 189);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-190', '2026-03-18', 'LIVREE', 34.9, 1.3, 190);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-191', '2026-04-29', 'NON_PLANIFIEE', 21.6, 1.0, 191);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-192', '2026-04-28', 'NON_PLANIFIEE', 70.2, 1.6, 192);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-193', '2026-03-29', 'NON_PLANIFIEE', 10.9, 0.2, 193);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-194', '2026-03-29', 'NON_PLANIFIEE', 39.0, 1.6, 194);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-195', '2026-03-21', 'NON_PLANIFIEE', 47.2, 0.2, 195);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-196', '2026-03-27', 'NON_PLANIFIEE', 65.1, 1.6, 196);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-197', '2026-04-18', 'LIVREE', 72.5, 1.8, 197);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-198', '2026-03-16', 'NON_PLANIFIEE', 39.4, 1.9, 198);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-199', '2026-04-17', 'NON_PLANIFIEE', 71.1, 1.5, 199);
INSERT INTO commande_entity (numero_commande, date_limite, statut, poids, volume, adresse_id) VALUES ('CMD-200', '2026-04-30', 'NON_PLANIFIEE', 36.0, 1.0, 200);

-- =============================================
-- PRODUITS LIES AUX COMMANDES
-- =============================================
UPDATE produit_entity SET commande_id = 'CMD-002' WHERE reference = 'PROD-001';
UPDATE produit_entity SET commande_id = 'CMD-003' WHERE reference = 'PROD-002';
UPDATE produit_entity SET commande_id = 'CMD-006' WHERE reference = 'PROD-003';
UPDATE produit_entity SET commande_id = 'CMD-007' WHERE reference = 'PROD-004';
UPDATE produit_entity SET commande_id = 'CMD-010' WHERE reference = 'PROD-005';
UPDATE produit_entity SET commande_id = 'CMD-012' WHERE reference = 'PROD-006';
UPDATE produit_entity SET commande_id = 'CMD-013' WHERE reference = 'PROD-007';
UPDATE produit_entity SET commande_id = 'CMD-014' WHERE reference = 'PROD-008';
UPDATE produit_entity SET commande_id = 'CMD-016' WHERE reference = 'PROD-009';
UPDATE produit_entity SET commande_id = 'CMD-017' WHERE reference = 'PROD-010';
UPDATE produit_entity SET commande_id = 'CMD-018' WHERE reference = 'PROD-011';
UPDATE produit_entity SET commande_id = 'CMD-019' WHERE reference = 'PROD-012';
UPDATE produit_entity SET commande_id = 'CMD-020' WHERE reference = 'PROD-013';
UPDATE produit_entity SET commande_id = 'CMD-022' WHERE reference = 'PROD-014';
UPDATE produit_entity SET commande_id = 'CMD-024' WHERE reference = 'PROD-015';
UPDATE produit_entity SET commande_id = 'CMD-025' WHERE reference = 'PROD-016';
UPDATE produit_entity SET commande_id = 'CMD-026' WHERE reference = 'PROD-017';
UPDATE produit_entity SET commande_id = 'CMD-027' WHERE reference = 'PROD-018';
UPDATE produit_entity SET commande_id = 'CMD-028' WHERE reference = 'PROD-019';
UPDATE produit_entity SET commande_id = 'CMD-029' WHERE reference = 'PROD-020';

