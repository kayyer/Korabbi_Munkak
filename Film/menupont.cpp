#include "menupont.h"
#include <iostream>
#include <fstream>
#include <string>
#include <limits>


Menu::Menu() { valaszto = 0; cim = ""; }
void Menu::hozzaad(filmtar& tar) {


	do
	{
		std::cout << "Hogyan szeretne hozzaadni a tarolohoz ?\n1 - Fajlbol\n2 - Consolerol\n";
		std::cin >> valaszto;

		if (valaszto == 1)
		{
			std::string file;
			std::ifstream fs;
			std::cout << "Adja meg a file nevet ahonnan beolvasni szeretne: " << std::endl;
			std::cin >> file;
			fs.open(file);
			if (fs.is_open())
			{
				while (!fs.eof())
				{
					try {
						tar.beolvas(fs);
					}
					catch (const char* p)
					{
						std::cout << "HIBA: " << p << std::endl;
						break;

					}
				}
				fs.close();
			}
			else
			{
				std::cout << "nem sikerult megnyitni a fajlt\n";
				return;
			}
		}
		else if (valaszto == 2)
		{
			try {
				tar.beolvas(std::cin);
			}
			catch (const char* p)
			{
				std::cout << "HIBA: " << p << std::endl;

			}
		}
		else
		{
			std::cout << "Csak 1-et vagy 2-ot valaszthat\n";
			std::cin.clear();
			std::cin.ignore(10000, '\n');
		}
		std::cout << "Jelenleg a hozzaado menupontban van.";
	} while (kilep());
}
void Menu::torol(filmtar& tar) {


	do
	{
		std::cout << "Mit szeretne torolni ?\n1 - Minden filmet.\n2 - Egy adott filmet\n";
		std::cin >> valaszto;
		std::cin.ignore();
		if (valaszto == 1)
		{
			tar.del();
			std::cout << "Minden filmet sikeresen torolt" << std::endl;
		}
		else if (valaszto == 2)
		{

			std::cout << "Torlendo film cime: ";
			std::getline(std::cin, cim);
			try
			{
				tar.del(tar.keres(cim));
			}
			catch (const char* p) {
				std::cout << "HIBA: " << p << std::endl;

			}

		}
		else
		{
			std::cout << "Csak 1 es 2 kozul valaszthat\n";
			std::cin.clear();
			std::cin.ignore(10000, '\n');
		}
		std::cout << "Jelenleg a torles menupontban van.";
	} while (kilep());

}
bool Menu::kilep() {


	while (1)
	{


		std::cout << "Van itt meg teendoje ? 1 - Van  2 - Nincs\n";
		std::cin >> valaszto;
		if (valaszto == 1)
		{
			return  true;
		}
		else if (valaszto == 2)
		{
			return false;
		}
		std::cin.clear();
		std::cin.ignore(10000, '\n');

	}
}
void Menu::kiir(filmtar& tar)
{


	do
	{

		std::cout << "Mit szeretne kiirni ?\n1 - Adott film informaciot\n2 - Minden filmet\n";
		std::cin >> valaszto;
		if (valaszto == 1)
		{
			std::cout << "Irja be a film cimet: ";
			std::cin.ignore();
			std::getline(std::cin, cim);
			try {
				tar.keres(cim)->kiir();
			}
			catch (const char* p) { std::cout << p << std::endl; }
		}
		else if (valaszto == 2)
		{
			tar.kiir();
		}
		else {
			std::cout << "Csak 1 es 2 kozott valaszthat.\n";
			std::cin.clear();
			std::cin.ignore(10000, '\n');
		}
		std::cout << "Jelenleg a filminfo menupontban van.";


	} while (kilep());
}

