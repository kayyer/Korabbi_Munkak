#include "doku.h"


doku::doku(std::string title, double length, int date, std::string description) : film(title, length, date), leiras(description) {}
void doku::kiir() {
	film::kiir();
	std::cout << "A dokumentumfilm leirasa : " << leiras << std::endl;
}
doku::~doku() {}