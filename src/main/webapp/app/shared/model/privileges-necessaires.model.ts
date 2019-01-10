export interface IPrivilegesNecessaires {
    id?: number;
    nom?: string;
    poids?: number;
}

export class PrivilegesNecessaires implements IPrivilegesNecessaires {
    constructor(public id?: number, public nom?: string, public poids?: number) {}
}
