#define _CRT_SECURE_NO_WARNINGS
#include "filmtar.h"
#include <fstream>
#include <string>
#include "film.h"
#include "doku.h"
#include "csaladi.h"
#include <time.h>

filmtar::filmtar() {
	db = 0; filmek = NULL;
}
void filmtar::add(film* Film) {

	db++;
	if (db > 1)
	{
		int i;
		film** tmp = new film * [db];
		for (i = 0; i < db - 1; i++)
		{
			tmp[i] = filmek[i];
			if (Film->getcim() == filmek[i]->getcim())
			{
				delete[] tmp;
				db--;
				throw "Mar van ilyen cimu film a filmtarban.\n";

			}
		}
		tmp[i] = Film;
		delete[] filmek;
		filmek = new film * [db];
		for (i = 0; i < db; i++)
		{
			filmek[i] = tmp[i];
		}
		delete[] tmp;
	}
	else if (db == 1) {

		filmek = new film * [db];
		filmek[0] = Film;

	}
	else {
		throw "Ilyen nem lehet";
	}
}


void filmtar::del(film* torlendo)
{
	int masolando = 0;
	bool megvan = false;
	db--;
	film** tmp = new film * [db];
	for (int j = 0; j < db + 1; j++)
	{
		if (filmek[j] == torlendo)
		{
			delete filmek[j];
			j++;
			megvan = true;
		}
		if (masolando < db && j < db + 1)
		{
			tmp[masolando] = filmek[j];
			masolando++;

		}
	}
	if (megvan)
	{

		delete[] filmek;
		filmek = new film * [db];
		for (int j = 0; j < db; j++)
		{
			filmek[j] = tmp[j];
		}
	}
	delete[] tmp;
	if (!megvan) {
		db++;
		throw "Nincs benne a torlendo film a filmtarban.";
	}
}


void filmtar::del() {
	for (int mov = 0; mov < db; mov++)
	{
		delete filmek[mov];
	}
	db = 0;
}

filmtar::~filmtar() {
	del();
	delete[] filmek;
}

film* filmtar::keres(std::string kerescim) {

	int i = 0;
	while (i < db && kerescim != filmek[i]->getcim())
		i++;
	if (i == db)
		throw "Nincs benne a filmtarban a keresett film";
	else
		return filmek[i];

}

void filmtar::kiir() {

	if (db == 0)
	{
		std::cout << "Nincsenek filmek a filmtarban.\n";
		return;
	}
	std::cout << "-------------------------------------------------------------------\n";
	for (int movies = 0; movies < db; movies++)
	{
		filmek[movies]->kiir();
		std::cout << "-------------------------------------------------------------------\n";

	}

}
void filmtar::beolvas(std::istream& is) {
	time_t ido = time(NULL);
	struct tm* aTime = localtime(&ido);

	std::string cim, leiras;
	int fajta = 0, megjelenes, korhatar;
	double hossz;
	if (&is == &std::cin)
		std::cout << "Film fajta(1-Normal film , 2-Dokumentumfilm , 3-Csaladi film): ";

	if (!(is >> fajta) || fajta < 1 || fajta > 3)
	{
		is.clear();
		is.ignore(10000, '\n');
		throw "Nem valasztott megfelelo film tipust.";
	}
	is.ignore();
	if (&is == &std::cin)
		std::cout << "\nFilm cime: ";

	std::getline(is, cim);
	if (cim == "")
		throw "Nincs cim megadva";

	if (&is == &std::cin)
		std::cout << "\nFilm hossza: ";

	if (!(is >> hossz) || hossz <= 0)
	{
		is.clear();
		is.ignore(10000, '\n');
		throw "A hossza csak 0nal nagyobb szam lehet";
	}
	if (&is == &std::cin)
		std::cout << "\nFilm megjelenesi eve: ";

	if (!(is >> megjelenes) || megjelenes < 0 || megjelenes > aTime->tm_year + 1900)
	{
		std::cin.clear();
		std::cin.ignore(10000, '\n');
		throw "A megjelenes eve csak pozitiv szam lehet es nem lehet nagyobb az idei evnel";
	}
	is.ignore();
	switch (fajta)
	{
	case 1:
	{
		film* fp = new film(cim, hossz, megjelenes);
		try {
			add(fp);
		}
		catch (const char* p) {
			std::cout << p << std::endl;
			delete fp;

		}
		break;
	}
	case 2:
	{
		if (&is == &std::cin) std::cout << "\nFilm leirasa: ";
		std::getline(is, leiras);
		if (leiras == "") throw "Meg kell adjon leirast";
		doku* dp = new doku(cim, hossz, megjelenes, leiras);
		try {
			add(dp);
		}
		catch (const char* p) {
			std::cout << p << std::endl;
			delete dp;


		}
		break;
	}
	case 3:
	{
		if (&is == &std::cin) std::cout << "\nFilm korhatara: ";
		is >> korhatar;
		if (korhatar < 0) throw "korhatar nem lehet negativ";
		csaladi* csp = new csaladi(cim, hossz, megjelenes, korhatar);
		try {
			add(csp);
		}
		catch (const char* p) {
			std::cout << p << std::endl;
			delete csp;


		}
		break;
	}
	}
}

