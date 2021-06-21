#include "csaladi.h"

csaladi::csaladi(std::string title, double length, int date, int korhatar) :film(title, length, date), korhatar(korhatar) {}
void csaladi::kiir() {

	film::kiir();
	std::cout << "A csaladi film korhatara: " << korhatar << std::endl;

}
csaladi::~csaladi() {}