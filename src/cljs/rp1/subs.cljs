(ns rp1.subs
  (:require
   [re-frame.core :as re-frame]
   [re-posh.core :as re-posh]))

; (re-frame/reg-sub
;  ::name
;  (fn [db]
;    (:name db)))
;
; (re-frame/reg-sub
;  ::active-panel
;  (fn [db _]
;    (:active-panel db))

 (re-posh/reg-sub
  ::create-todo-form-id
  (fn [_ _]
    {:type :query
     :query '[:find ?id .
              :where [?id :app/type :type/create-todo-form]]}))

 (re-posh/reg-sub
  ::create-todo-form
  :<- [::create-todo-form-id]
  (fn [id _]
    {:type    :pull
     :pattern '[:db/id :create-todo-form/title]
     :id      id}))

 (re-posh/reg-sub
  ::task-ids
  (fn [_ _]
    {:type :query
     :query '[:find  [?tid ...]
              :where [?tid :app/type :type/task]]}))

 (re-posh/reg-sub
  ::task
  (fn [_ [_ id]]
    {:type    :pull
     :pattern '[:db/id :task/done? :task/title]
     :id      id}))
