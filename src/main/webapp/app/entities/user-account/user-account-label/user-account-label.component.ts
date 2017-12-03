import { Component, OnInit, Input } from '@angular/core';

import { UserAccount } from '../user-account.model';
import { UserAccountService } from '../user-account.service';

@Component({
    selector: 'app-user-account-label',
    templateUrl: './user-account-label.component.html'
})
export class UserAccountLabelComponent implements OnInit {
    @Input()
    id: number;
    userAccount: UserAccount;

    constructor(
        private userService: UserAccountService
    ) { }

    ngOnInit() {
        this.load(this.id);
    }

    load(id) {
        this.userService.find(id).subscribe((userAccount) => {
            this.userAccount = userAccount;
        });
    }
    previousState() {
        window.history.back();
    }
}
