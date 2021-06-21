#include <iostream>
#include "csaladi.h"
#include "doku.h"
#include "film.h"
#include "filmtar.h"
#include "menupont.h"



int main()
{
	filmtar tarolo;
	int userValasz;
	Menu menu;
	bool nvege = true;
	do
	{
		std::cout << "Mit szeretne csinalni?\n1 - Hozzaadni\n2 - Torolni\n3 - Filminfot megtudni\n4 - Kilepes\n";
		std::cin >> userValasz;
		switch (userValasz)
		{
		case 1:
			menu.hozzaad(tarolo);
			break;
		case 2:
			menu.torol(tarolo);
			break;

		case 3:
			menu.kiir(tarolo);
			break;
		case 4:
			std::cout << "A kilepest valasztotta. Biztonsagi kerdes : ";
			nvege = menu.kilep();
			break;
		default:
			std::cout << "Irja be a szamat a valasztott menupontnak\n";
		}


		std::cin.clear();
		std::cin.ignore(10000, '\n');
	} while (nvege);

	return 0;
}
