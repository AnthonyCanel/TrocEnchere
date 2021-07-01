USE [msdb]
GO

/****** Object:  Job [UpdateEnchere]    Script Date: 01/07/2021 16:46:17 ******/
BEGIN TRANSACTION
DECLARE @ReturnCode INT
SELECT @ReturnCode = 0
/****** Object:  JobCategory [Full-Text]    Script Date: 01/07/2021 16:46:17 ******/
           IF NOT EXISTS (SELECT name FROM msdb.dbo.syscategories WHERE name=N'Full-Text' AND category_class=1)
BEGIN
EXEC @ReturnCode = msdb.dbo.sp_add_category @class=N'JOB', @type=N'LOCAL', @name=N'Full-Text'
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback

END

DECLARE @jobId BINARY(16)
EXEC @ReturnCode =  msdb.dbo.sp_add_job @job_name=N'UpdateEnchere',
		@enabled=1,
		@notify_level_eventlog=0,
		@notify_level_email=0,
		@notify_level_netsend=0,
		@notify_level_page=0,
		@delete_level=0,
		@description=N'Mise à jour de la table ENCHERES selon la date du  jour et la date de fin d''enchères renseignées et ensuite mise à jour de la table ARTICLES en fonction de la date du jour
',
		@category_name=N'Full-Text',
		@owner_login_name=N'sa', @job_id = @jobId OUTPUT
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
/****** Object:  Step [MAJ_TABLES_ENCHERES_ARTICLES]    Script Date: 01/07/2021 16:46:17 ******/
EXEC @ReturnCode = msdb.dbo.sp_add_jobstep @job_id=@jobId, @step_name=N'MAJ_TABLES_ENCHERES_ARTICLES',
		@step_id=1,
		@cmdexec_success_code=0,
		@on_success_action=1,
		@on_success_step_id=0,
		@on_fail_action=2,
		@on_fail_step_id=0,
		@retry_attempts=0,
		@retry_interval=0,
		@os_run_priority=0, @subsystem=N'TSQL',
		@command=N'BEGIN
UPDATE ENCHERES SET etat_enchere = ''Vendu'', no_acquereur = no_utilisateur WHERE GETDATE() > (SELECT date_fin_encheres FROM ARTICLES WHERE ENCHERES.no_article = ARTICLES.no_article)  AND etat_enchere <> ''Vendu''

UPDATE ARTICLES SET etat_article = ''nondisponible'', prix_vente = (SELECT montant_enchere FROM ENCHERES WHERE ENCHERES.no_article = ARTICLES.no_article  AND der_ench = 1) WHERE GETDATE() > date_fin_encheres AND etat_article <> ''nondisponible''
END;',
		@database_name=N'TROC_ENCHERES',
		@flags=0
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
EXEC @ReturnCode = msdb.dbo.sp_update_job @job_id = @jobId, @start_step_id = 1
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
EXEC @ReturnCode = msdb.dbo.sp_add_jobschedule @job_id=@jobId, @name=N'UpdateEnchere',
		@enabled=1,
		@freq_type=4,
		@freq_interval=1,
		@freq_subday_type=8,
		@freq_subday_interval=1,
		@freq_relative_interval=0,
		@freq_recurrence_factor=0,
		@active_start_date=20210630,
		@active_end_date=99991231,
		@active_start_time=0,
		@active_end_time=235959,
		@schedule_uid=N'c7e06e1e-9de8-4dea-b210-7d253c5f756a'
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
EXEC @ReturnCode = msdb.dbo.sp_add_jobserver @job_id = @jobId, @server_name = N'(local)'
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
COMMIT TRANSACTION
GOTO EndSave
QuitWithRollback:
    IF (@@TRANCOUNT > 0) ROLLBACK TRANSACTION
EndSave:
GO