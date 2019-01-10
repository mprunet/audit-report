import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IComposantImpacte } from 'app/shared/model/composant-impacte.model';

type EntityResponseType = HttpResponse<IComposantImpacte>;
type EntityArrayResponseType = HttpResponse<IComposantImpacte[]>;

@Injectable({ providedIn: 'root' })
export class ComposantImpacteService {
    public resourceUrl = SERVER_API_URL + 'api/composant-impactes';

    constructor(protected http: HttpClient) {}

    create(composantImpacte: IComposantImpacte): Observable<EntityResponseType> {
        return this.http.post<IComposantImpacte>(this.resourceUrl, composantImpacte, { observe: 'response' });
    }

    update(composantImpacte: IComposantImpacte): Observable<EntityResponseType> {
        return this.http.put<IComposantImpacte>(this.resourceUrl, composantImpacte, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IComposantImpacte>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IComposantImpacte[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
