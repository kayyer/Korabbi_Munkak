#ifndef MENUPONT_H_INCLUDED
#define MENUPONT_H_INCLUDED
#include <iostream>
#include "filmtar.h"




class Menu {
	int valaszto;
	std::string cim;
public:
	Menu();
	void hozzaad(filmtar& tar);
	void torol(filmtar& tar);
	void kiir(filmtar& tar);
	bool kilep();

};


#endif // MENUPONT_H_INCLUDED
