import { IProjet } from 'app/shared/model//projet.model';
import { IPrivilegesNecessaires } from 'app/shared/model//privileges-necessaires.model';
import { IComposantImpacte } from 'app/shared/model//composant-impacte.model';

export interface IVulnerabilite {
    id?: number;
    ref?: string;
    categorie?: string;
    nom?: string;
    description?: string;
    probabilite?: number;
    impact?: number;
    criticite?: string;
    cvss?: number;
    recommandation?: string;
    projet?: IProjet;
    privilegesNecessaires?: IPrivilegesNecessaires;
    composantImpacte?: IComposantImpacte;
}

export class Vulnerabilite implements IVulnerabilite {
    constructor(
        public id?: number,
        public ref?: string,
        public categorie?: string,
        public nom?: string,
        public description?: string,
        public probabilite?: number,
        public impact?: number,
        public criticite?: string,
        public cvss?: number,
        public recommandation?: string,
        public projet?: IProjet,
        public privilegesNecessaires?: IPrivilegesNecessaires,
        public composantImpacte?: IComposantImpacte
    ) {}
}
