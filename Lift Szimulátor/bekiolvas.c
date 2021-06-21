#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "bekiolvas.h"



utas*  beolvas(int* scnt,tulsok *tul)
{
    FILE *be;
    be = fopen("teszt.txt", "rt");
    if (be == NULL)
    {
        perror("Hiba a megnyitasnal");
        exit(EXIT_FAILURE);
    }

    char ch;
    int cnt = 0;
    char *ide;
    utas *ember;
    bool terheles = true;



    while ((ch = fgetc(be)) != EOF)
    {
        cnt++;
    }
    if (cnt == 0)
    {
        printf("0 beolvasott karakter van");
        exit(EXIT_FAILURE);
    }
    ember = (utas*)malloc(1 * sizeof(utas));


    ide = (char*)malloc((cnt + 1) * sizeof(char));
    rewind(be);

    while (fgets(ide, cnt+1, be))
    {
        if (terheles)
        {
            if ((sscanf(ide, "%d,%d", &((*tul).suly), &((*tul).fo)) == 2));
            else
            {
                printf("NINCS MAXIMIMUM SULY VAGY/ES FO");
                exit(EXIT_FAILURE);
            }
            terheles = false;
        }
        else
        {
            if ((sscanf(ide, "%d,%d,%d,%d:%d:%d,%d", &ember[*scnt].id, &ember[*scnt].honnan, &ember[*scnt].hova, &ember[*scnt].ido.ora, &ember[*scnt].ido.perc, &ember[*scnt].ido.mp, &ember[*scnt].suly)) == 7)
            {
                if (ember[*scnt].honnan == ember[*scnt].hova || ember[*scnt].hova < 0 || ember[*scnt].honnan < 0 || ember[*scnt].suly < 0 || ember[*scnt].suly > (*tul).suly || ember[*scnt].ido.ora>24|| ember[*scnt].ido.ora < 0||ember[*scnt].ido.perc > 60|| ember[*scnt].ido.perc < 0||ember[*scnt].ido.mp > 60|| ember[*scnt].ido.mp <0 )
                    exit(EXIT_FAILURE);
                *scnt += 1;
                ember = (utas*)realloc(ember, sizeof(utas)*(*scnt + 1));

            }
        }

    }
    free(ide);
    fclose(be);

    return ember;
}

void kiir(double varatl, int *orak, int emeletsporol, double emeletsporolszazalek, maxmin ai)
{
    FILE* fki = fopen("tesztki.txt", "wt");
    if (fki == NULL)
    {
        printf("NEM SIKERULT BEOLVASNI");
        exit(EXIT_FAILURE);
    }
    int osszutas = 0;
    for (int i = 0; i < 24; i++)
    {
        fprintf(fki, "|%.2d orakor : %d utas volt|\n", i, orak[i]);
        osszutas += orak[i];
    }
    fprintf(fki, "\n\nAtlagosan %.2lf mp volt a varakozasi ido\n", varatl);
    fprintf(fki, "Osszesen %d utas volt\n", osszutas);
    fprintf(fki, "%d emelettel ment kevesebbet ,mintha mindenkit kulon szedett volna fel (%.2lf .-e)\n", emeletsporol, emeletsporolszazalek);
    fprintf(fki, "A legtobbet %d vart ez %dmp volt\n", ai.max_ki, ai.max_mennyit);
    fprintf(fki, "A legkevesebbet %d vart ez %dmp volt\n", ai.min_ki, ai.min_mennyit);


    fclose(fki);



}


