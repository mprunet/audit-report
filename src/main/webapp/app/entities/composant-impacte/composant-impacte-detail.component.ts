import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IComposantImpacte } from 'app/shared/model/composant-impacte.model';

@Component({
    selector: 'jhi-composant-impacte-detail',
    templateUrl: './composant-impacte-detail.component.html'
})
export class ComposantImpacteDetailComponent implements OnInit {
    composantImpacte: IComposantImpacte;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ composantImpacte }) => {
            this.composantImpacte = composantImpacte;
        });
    }

    previousState() {
        window.history.back();
    }
}
