import {initNavbar} from "./navbar.js";
import {initScreen} from "./screen.js";
import {initNext} from "./pageMenu.js";
import {initFilter} from "./filter.js";
import {initPaginator} from "./paginator.js";
import {initRanking} from "./ranking.js";
import {initNumberInput} from "./util.js";

document.addEventListener('DOMContentLoaded', () => {

    initScreen();
    initNavbar();
    initNumberInput();
    initNext();
    initFilter();
    initPaginator();
    initRanking();
});