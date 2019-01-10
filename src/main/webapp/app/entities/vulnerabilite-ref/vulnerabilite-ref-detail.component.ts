import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVulnerabiliteRef } from 'app/shared/model/vulnerabilite-ref.model';

@Component({
    selector: 'jhi-vulnerabilite-ref-detail',
    templateUrl: './vulnerabilite-ref-detail.component.html'
})
export class VulnerabiliteRefDetailComponent implements OnInit {
    vulnerabiliteRef: IVulnerabiliteRef;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vulnerabiliteRef }) => {
            this.vulnerabiliteRef = vulnerabiliteRef;
        });
    }

    previousState() {
        window.history.back();
    }
}
