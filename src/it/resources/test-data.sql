SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE clients;
TRUNCATE TABLE assets;
TRUNCATE TABLE income_streams;
TRUNCATE TABLE calculations;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO clients (first_name, last_name, date_of_birth, gender, homeowner_status_id, relationship_status_id, partner_id) VALUES
  ('Max', 'Power', '1940-01-01', 'Male', 2, 1, null),
  ('Homer', 'Simpson', '1953-08-09', 'Male', 1, 2, null),
  ('Seymour', 'Skinner', '1953-08-09', 'Male', 1, 1, null),
  ('Ned', 'Flanders', '1953-08-09', 'Male', 1, 1, null),
  ('Marge', 'Simpson', '1953-08-09', 'Female', 1, 2, null),
  ('Marie', 'Nelson', '1946-07-12', 'Female', 1, 1, null),
  ('Mary', 'Cassidy', '1947-03-06', 'Female', 1, 2, null),
  ('Bain', 'Simpson', '1945-07-29', 'Male', 1, 2, null);

UPDATE clients SET partner_id = 5 WHERE id = 2;
UPDATE clients SET partner_id = 2 WHERE id = 5;
UPDATE clients SET partner_id = 8 WHERE id = 7;
UPDATE clients SET partner_id = 7 WHERE id = 8;

INSERT INTO calculations (client_id, date, payment, applicable_test, comment) VALUES
  (1, '2017-04-01', 546.94, 'Assets Test', 'Initial calculation'),
  (1, '2017-06-14', 558.11, 'Assets Test', 'Purchased funeral bond'),
  (1, '2017-09-25', 365.39, 'Income Test', 'Started part time job'),
  (6, '2017-11-10', 599.13, 'Assets Test', 'Initial calculation'),
  (7, '2017-11-10', 290.41, 'Assets Test', 'Initial calculation');

INSERT INTO assets (calculation_id, asset_type_id, description, value) VALUES
  (1, 1, 'Centrelink value', 5000),
  (1, 2, '2001 Honda CRV', 4000),
  (1, 8, 'CBA shares', 250000),
  (1, 9, 'Magellan Global Fund', 70200),
  (1, 12, 'Russell ABP', 100000),
  (1, 13, 'Russell ABP2', 80000),
  (2, 1, 'Centrelink value', 5000),
  (2, 2, '2001 Honda CRV', 4000),
  (2, 8, 'CBA shares', 250000),
  (2, 9, 'Magellan Global Fund', 70200),
  (2, 12, 'Russell ABP', 100000),
  (2, 13, 'Russell ABP2', 80000),
  (3, 1, 'Centrelink value', 5000),
  (3, 2, '2001 Honda CRV', 4000),
  (3, 8, 'CBA shares', 250000),
  (3, 9, 'Magellan Global Fund', 70200),
  (3, 12, 'Russell ABP', 100000),
  (3, 13, 'Russell ABP2', 80000),
  (4, 1, 'Centrelink value', 5000),
  (4, 2, 'Motor vehicles', 15000),
  (5, 1, 'Centrelink value', 8000),
  (5, 2, '2007 Toyota Camry (Mary)', 6500),
  (5, 2, '2004 Mitsubishi Pajero (Bain)', 3000),
  (5, 20, 'Civil Celebrant Assets', 2831),
  (5, 10, 'Westpac Savings Account - 68-5969', 41410),
  (5, 10, 'Westpac Savings Account - 62-2096', 3000),
  (5, 10, 'BankWest - 0034050', 2614),
  (5, 10, 'NAB Cheque Account - 505935768', 1565),
  (5, 10, 'Westpac Savings Account - 35-6762', 4513),
  (5, 9, 'CFS Managed Investments', 94853),
  (5, 18, 'Synergy', 12128),
  (5, 12, 'ABP (Mary)', 147023),
  (5, 12, 'CFGS ABP (Bain)', 161333),
  (5, 17, 'Challenger Annuity (Mary)', 147591);

INSERT INTO income_streams (calculation_id, income_stream_type_id, description, deductible_amount, annual_payment, current_balance, assessed_income) VALUES
  (1, 2, 'Russell ABP', 3750, 8000, 100000, 4250),
  (1, 4, 'Challenger Lifetime Annuity', null, null, null, 2800),
  (2, 2, 'Russell ABP', 3750, 8000, 100000, 4250),
  (2, 4, 'Challenger Lifetime Annuity', null, null, null, 2800),
  (3, 2, 'Russell ABP', 3750, 8000, 100000, 4250),
  (3, 4, 'Challenger Lifetime Annuity', null, null, 50000, 2800),
  (5, 2, 'ABP (Mary)', 6745, 8647, null, 1902),
  (5, 2, 'CFGS ABP (Bain)', 9723, 17358, null, 7635),
  (5, 4, 'Challenger Annuity (Mary)', 6938, 7878, null, 940),
  (5, 7, 'Austrian Pension (Bain)', null, null, null, 115.31),
  (5, 1, 'Civil Celebrant (Mary)', null, null, null, 3954),
  (5, 7, 'CHWASTA Muir''s (Bain)', null, null, null, 728);