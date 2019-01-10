import { IVulnerabilite } from 'app/shared/model//vulnerabilite.model';

export interface IVulnerabiliteRef {
    id?: number;
    nom?: string;
    description?: string;
    probabilite?: number;
    impact?: number;
    criticite?: string;
    cvss?: number;
    recommandation?: string;
    vulnRef?: IVulnerabilite;
}

export class VulnerabiliteRef implements IVulnerabiliteRef {
    constructor(
        public id?: number,
        public nom?: string,
        public description?: string,
        public probabilite?: number,
        public impact?: number,
        public criticite?: string,
        public cvss?: number,
        public recommandation?: string,
        public vulnRef?: IVulnerabilite
    ) {}
}
