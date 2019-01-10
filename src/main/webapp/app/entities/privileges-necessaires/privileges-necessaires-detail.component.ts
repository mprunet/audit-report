import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPrivilegesNecessaires } from 'app/shared/model/privileges-necessaires.model';

@Component({
    selector: 'jhi-privileges-necessaires-detail',
    templateUrl: './privileges-necessaires-detail.component.html'
})
export class PrivilegesNecessairesDetailComponent implements OnInit {
    privilegesNecessaires: IPrivilegesNecessaires;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ privilegesNecessaires }) => {
            this.privilegesNecessaires = privilegesNecessaires;
        });
    }

    previousState() {
        window.history.back();
    }
}
