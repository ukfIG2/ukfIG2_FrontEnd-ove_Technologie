export interface Tovar {
    ID: number;
    Znacka: string;
    Modelova_rada: string;
    Nazov: string;
    Procesor: string;
    Velkost_operacnej_pamate: string;
    Uhlopriecka_displeja: string;
    Fotka: string;
    Cena: number;
  }
  
  export interface Data {
    tovars: Tovar[];
  }