import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { throwError } from 'rxjs';
import { Cookie } from 'ng2-cookies';

export class Product {
  constructor(public id: number, public name: string, public price: number) { }
} 

@Injectable({
  providedIn: 'root'
})
export class AppServiceService {
  
  public clientId = 'newClient';
  public redirectUri = 'http://localhost:8089/';
  constructor(private _http: HttpClient) { }

  retrieveToken(code: any) {
    let params = new URLSearchParams();   
    params.append('grant_type','authorization_code');
    params.append('client_id', this.clientId);
    params.append('client_secret', 'newClientSecret');
    params.append('redirect_uri', this.redirectUri);
    params.append('code',code);

    let headers = 
      new HttpHeaders({'Content-type': 'application/x-www-form-urlencoded; charset=utf-8'});
       
      this._http.post('http://localhost:8083/auth/realms/ismael/protocol/openid-connect/token', 
        params.toString(), { headers: headers })
        .subscribe({
          next: (d) => this.saveToken(d),
          error: (err) => alert(`Invalid Credentials ${err}`),
          complete: () => console.info('complete') }); 
  }
  saveToken(token: any) {
    const expireDate = new Date().getTime() + (1000 * token.expires_in);
    Cookie.set('access_token', token.access_token, expireDate);
    console.log('Obtained Access token');
    window.location.href = 'http://localhost:8089';
  }

  getResource(resourceUrl: any) : Observable<any> {
    const jsonHeaders = {
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8', 
      'Authorization': `Bearer ${Cookie.get('access_token')}`
    };
    const headers = new HttpHeaders(jsonHeaders);
    
    try {
      return this._http.get(resourceUrl, { headers: headers })
    } catch (err: any){
      return throwError(() => new Error(err.json().error || 'Server error'));
}
  }

  checkCredentials() {
    return Cookie.check('access_token');
  } 

  logout() {
    Cookie.delete('access_token');
    window.location.reload();
  }

}
