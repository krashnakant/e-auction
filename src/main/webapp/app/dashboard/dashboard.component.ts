import { Component, OnInit } from '@angular/core';
import { Account, Principal } from '../shared';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: [
        'dashboard.scss'
    ]
})
export class DashboardComponent implements OnInit {

    constructor(
        private principal: Principal
    ) {
    }

    ngOnInit() { }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

}
