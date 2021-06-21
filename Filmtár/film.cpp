#include "film.h"


film::film(std::string title, double length, int date) :cim(title), hossz(length), megjelenes(date) {}
std::string film::getcim() { return cim; }
void film::kiir() {

	std::cout << "A film cime: " << cim << std::endl;
	std::cout << "A film hossza: " << hossz << " perc" << std::endl;
	std::cout << "A film megjelenese: " << megjelenes << std::endl;

}
film::~film() {}
void film::set(std::string a, double b, int c) {

	cim = a;
	hossz = b;
	megjelenes = c;
}