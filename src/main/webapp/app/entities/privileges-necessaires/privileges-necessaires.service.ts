import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPrivilegesNecessaires } from 'app/shared/model/privileges-necessaires.model';

type EntityResponseType = HttpResponse<IPrivilegesNecessaires>;
type EntityArrayResponseType = HttpResponse<IPrivilegesNecessaires[]>;

@Injectable({ providedIn: 'root' })
export class PrivilegesNecessairesService {
    public resourceUrl = SERVER_API_URL + 'api/privileges-necessaires';

    constructor(protected http: HttpClient) {}

    create(privilegesNecessaires: IPrivilegesNecessaires): Observable<EntityResponseType> {
        return this.http.post<IPrivilegesNecessaires>(this.resourceUrl, privilegesNecessaires, { observe: 'response' });
    }

    update(privilegesNecessaires: IPrivilegesNecessaires): Observable<EntityResponseType> {
        return this.http.put<IPrivilegesNecessaires>(this.resourceUrl, privilegesNecessaires, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPrivilegesNecessaires>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPrivilegesNecessaires[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
