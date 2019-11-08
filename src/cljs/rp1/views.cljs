(ns rp1.views
  (:require
   [re-frame.core :as re-frame]
   [re-posh.core :refer [subscribe dispatch]]
   [re-com.core :as re-com]
   [rp1.subs :as subs]
   [rp1.events :as events]))

;; Create todo form
(defn render-create-todo-form [form]
  (let [{id    :db/id
         title :create-todo-form/title} form]
    [:div.create-task-panel
     [:input
      {:type "text"
       :value title
       :on-change #(dispatch [::events/set-todo-form-title id (-> % .-target .-value)])}]
     [:button.create-task-button
      {:on-click #(dispatch [::events/create-todo id title])}
      "Create"]]))

(defn create-todo-form []
  (let [form (subscribe [::subs/create-todo-form])]
    (fn []
      (render-create-todo-form @form))))

;; Task list item
(defn render-task-list-item [task]
  (let [{id :db/id
         done? :task/done?
         title :task/title} task]
    [:div.task-list-item
     [:input {:type "checkbox"
              :ckecked (if done? "true" nil)
              :on-change #(dispatch [::events/set-task-status id (not done?)])}]
     [:span title]]))

(defn task-list-item [id]
  (let [task (subscribe [::subs/task id])]
    (fn []
      (render-task-list-item @task))))

;; Task list
(defn task-list []
  (let [task-ids (subscribe [::subs/task-ids])]
    (fn []
      [:div.task-list
       (for [task-id @task-ids]
         ^{:key task-id} [task-list-item task-id])])))

(defn main-panel []
  [:div.main-panel
   [:h1 "TodoMVC"]
   [create-todo-form]
   [task-list]])

; ;; home
;
; (defn home-title []
;   (let [name (re-frame/subscribe [::subs/name])]
;     [re-com/title
;      :label (str "Hello from " @name ". This is the Home Page.")
;      :level :level1]))
;
; (defn link-to-about-page []
;   [re-com/hyperlink-href
;    :label "go to About Page"
;    :href "#/about"])
;
; (defn home-panel []
;   [re-com/v-box
;    :gap "1em"
;    :children [[home-title]
;               [link-to-about-page]]])
;
;
;
; ;; about
;
; (defn about-title []
;   [re-com/title
;    :label "This is the About Page."
;    :level :level1])
;
; (defn link-to-home-page []
;   [re-com/hyperlink-href
;    :label "go to Home Page"
;    :href "#/"])
;
; (defn about-panel []
;   [re-com/v-box
;    :gap "1em"
;    :children [[about-title]
;               [link-to-home-page]]])
;
;
; ;; main
;
; (defn- panels [panel-name]
;   (case panel-name
;     :home-panel [home-panel]
;     :about-panel [about-panel]
;     [:div]))
;
; (defn show-panel [panel-name]
;   [panels panel-name])
;
; (defn main-panel []
;   (let [active-panel (re-frame/subscribe [::subs/active-panel])]
;     [re-com/v-box
;      :height "100%"
;      :children [[panels @active-panel]]]))
