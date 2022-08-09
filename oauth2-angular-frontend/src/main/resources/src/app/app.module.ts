import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ExtraOptions, RouterModule, Routes } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeHeaderComponent } from './home-header/home-header.component';
import { ProductDetailsComponent } from './product-details/product-details.component';

const routes: Routes = [{ path: '', component: HomeHeaderComponent, pathMatch: 'full' }];
const extraOptions: ExtraOptions = {onSameUrlNavigation: 'reload'};

@NgModule({
  declarations: [
    AppComponent,
    HomeHeaderComponent,
    ProductDetailsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    RouterModule.forRoot(routes, extraOptions)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
