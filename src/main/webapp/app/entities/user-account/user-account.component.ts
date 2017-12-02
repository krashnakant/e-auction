import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { UserAccount } from './user-account.model';
import { UserAccountService } from './user-account.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'app-user-account',
    templateUrl: './user-account.component.html'
})
export class UserAccountComponent implements OnInit, OnDestroy {
userAccounts: UserAccount[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private userAccountService: UserAccountService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.userAccountService.query().subscribe(
            (res: ResponseWrapper) => {
                this.userAccounts = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInUserAccounts();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: UserAccount) {
        return item.id;
    }
    registerChangeInUserAccounts() {
        this.eventSubscriber = this.eventManager.subscribe('userAccountListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
