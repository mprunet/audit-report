import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVulnerabilite } from 'app/shared/model/vulnerabilite.model';

@Component({
    selector: 'jhi-vulnerabilite-detail',
    templateUrl: './vulnerabilite-detail.component.html'
})
export class VulnerabiliteDetailComponent implements OnInit {
    vulnerabilite: IVulnerabilite;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vulnerabilite }) => {
            this.vulnerabilite = vulnerabilite;
        });
    }

    previousState() {
        window.history.back();
    }
}
