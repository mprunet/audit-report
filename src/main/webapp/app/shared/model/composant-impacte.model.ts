export interface IComposantImpacte {
    id?: number;
    nom?: string;
}

export class ComposantImpacte implements IComposantImpacte {
    constructor(public id?: number, public nom?: string) {}
}
