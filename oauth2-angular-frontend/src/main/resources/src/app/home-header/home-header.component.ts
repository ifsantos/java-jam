import { Component, OnInit } from '@angular/core';
import { AppServiceService } from '../app-service.service';

@Component({
  selector: 'app-home-header',
  templateUrl: './home-header.component.html',
  styleUrls: ['./home-header.component.scss']
})
export class HomeHeaderComponent implements OnInit {
  public isLoggedIn = false;
  auth_server = 'http://localhost:8083';

  constructor(private _service: AppServiceService) { }

  ngOnInit(): void {
    this.isLoggedIn = this._service.checkCredentials();    
    let i = window.location.href.indexOf('code');
    if(!this.isLoggedIn && i != -1) {
      this._service.retrieveToken(window.location.href.substring(i + 5));
    }
  }
  login() {
    window.location.href = this.auth_server +
      '/auth/realms/ismael/protocol/openid-connect/auth?response_type=code&scope=openid%20write%20read&client_id=' + 
         this._service.clientId + '&redirect_uri='+ this._service.redirectUri;
    }
 
  logout() {
    this._service.logout();
  }
  
}
