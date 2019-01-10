import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProjet } from 'app/shared/model/projet.model';

type EntityResponseType = HttpResponse<IProjet>;
type EntityArrayResponseType = HttpResponse<IProjet[]>;

@Injectable({ providedIn: 'root' })
export class ProjetService {
    public resourceUrl = SERVER_API_URL + 'api/projets';

    constructor(protected http: HttpClient) {}

    create(projet: IProjet): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(projet);
        return this.http
            .post<IProjet>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(projet: IProjet): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(projet);
        return this.http
            .put<IProjet>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IProjet>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IProjet[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(projet: IProjet): IProjet {
        const copy: IProjet = Object.assign({}, projet, {
            dateRestitution:
                projet.dateRestitution != null && projet.dateRestitution.isValid() ? projet.dateRestitution.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.dateRestitution = res.body.dateRestitution != null ? moment(res.body.dateRestitution) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((projet: IProjet) => {
                projet.dateRestitution = projet.dateRestitution != null ? moment(projet.dateRestitution) : null;
            });
        }
        return res;
    }
}
