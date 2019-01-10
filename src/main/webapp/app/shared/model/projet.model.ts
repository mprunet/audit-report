import { Moment } from 'moment';

export interface IProjet {
    id?: number;
    basicat?: string;
    orangeCarto?: string;
    periscope?: string;
    version?: string;
    nom?: string;
    moe?: string;
    cdp?: string;
    rssi?: string;
    cssi?: string;
    ds?: string;
    dp?: string;
    typeAudit?: string;
    entrepriseAuditeur?: string;
    dateRestitution?: Moment;
}

export class Projet implements IProjet {
    constructor(
        public id?: number,
        public basicat?: string,
        public orangeCarto?: string,
        public periscope?: string,
        public version?: string,
        public nom?: string,
        public moe?: string,
        public cdp?: string,
        public rssi?: string,
        public cssi?: string,
        public ds?: string,
        public dp?: string,
        public typeAudit?: string,
        public entrepriseAuditeur?: string,
        public dateRestitution?: Moment
    ) {}
}
